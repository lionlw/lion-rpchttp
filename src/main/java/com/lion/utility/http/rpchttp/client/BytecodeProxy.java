package com.lion.utility.http.rpchttp.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.tool.log.LogLIB;
import com.lion.utility.http.rpchttp.constant.Constant;
import com.lion.utility.http.rpchttp.entity.DynamicParam;
import com.lion.utility.http.rpchttp.entity.HttpConfig;
import com.lion.utility.http.rpchttp.entity.InterfaceConfig;
import com.lion.utility.http.rpchttp.entity.MethodConfig;
import com.lion.utility.http.rpchttp.entity.RequestEntity;
import com.lion.utility.http.rpchttp.tool.CommonLIB;

/**
 * 字节码方式生成客户端代理
 * 
 * @author lion
 *
 */
public class BytecodeProxy {
	private BytecodeProxy() {
	}

	private static String rpcHttpServiceVar = "rpcHttpService";

	/**
	 * 基于接口生成代理类实例
	 * 
	 * @param rpcHttpService
	 *            rpcHttpService实例
	 * @param inferfaceClass
	 *            接口类
	 * @param <T>
	 *            泛型
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newProxyInstance(RPCHttpService rpcHttpService, Class<T> inferfaceClass) throws Exception {
		// 创建动态代理类名
		CtClass mCtc = rpcHttpService.rpcHttpClient.classPool.makeClass(inferfaceClass.getName() + "_httpproxy");

		// 添加接口
		mCtc.addInterface(rpcHttpService.rpcHttpClient.classPool.get(inferfaceClass.getName()));

		// 添加屬性  
		mCtc.addField(CtField.make("private " + rpcHttpService.getClass().getName() + " " + BytecodeProxy.rpcHttpServiceVar + ";", mCtc));

		// 参数构造器  
		CtConstructor constructor = new CtConstructor(new CtClass[] { rpcHttpService.rpcHttpClient.classPool.get(rpcHttpService.getClass().getName()) }, mCtc);
		constructor.setModifiers(Modifier.PUBLIC);
		constructor.setBody("{this." + BytecodeProxy.rpcHttpServiceVar + "=$1;}");
		mCtc.addConstructor(constructor);

		// 获取接口配置
		InterfaceConfig interfaceConfig = BytecodeProxy.getInterfaceConfig(inferfaceClass);

		// 添加方法
		Method[] methods = inferfaceClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			// 获取方法配置
			MethodConfig methodConfig = BytecodeProxy.getMethodConfig(method);
			// 检测并合并方法配置
			methodConfig = BytecodeProxy.mergeMethodConfig(interfaceConfig, methodConfig);

			//生成代理方法
			String methodCode = BytecodeProxy.generateMethodCode(method, rpcHttpService, methodConfig);

			//debug模式，则记录生成的字节码
			if (rpcHttpService.rpcHttpClient.rpcHttpClientConfig.getIsDebug()) {
				LogLIB.info(methodCode);
			}

			CtMethod cm = CtNewMethod.make(methodCode, mCtc);
			mCtc.addMethod(cm);
		}

		// 字节码生成
		Class<?> cls = mCtc.toClass();
		// 对象释放
		mCtc.detach();

		// 以指定构造进行实例化
		Constructor<?> con = cls.getConstructor(rpcHttpService.getClass());
		T instance = (T) con.newInstance(rpcHttpService);

		// debug模式，则记录生成的字节码
		if (rpcHttpService.rpcHttpClient.rpcHttpClientConfig.getIsDebug()) {
			CommonLIB.saveBytecodeFile(cls.getName(), mCtc.toBytecode());
		}

		return instance;
	}

	/**
	 * 生成方法代码
	 * 
	 * @param rpcHandlerClassName
	 *            rpc执行类路径
	 * @param method
	 *            方法对象
	 * @param rpcHttpService
	 *            rpcHttpService
	 * @param methodConfig
	 *            方法配置
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	private static String generateMethodCode(Method method, RPCHttpService rpcHttpService,
			MethodConfig methodConfig) throws Exception {
		String methodName = method.getName();
		String methodReturnType = method.getReturnType().getName();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Class<?>[] exceptionTypes = method.getExceptionTypes();

		//组装方法的Exception声明  
		StringBuilder exceptionBuffer = new StringBuilder();
		if (exceptionTypes.length > 0) {
			exceptionBuffer.append(" throws ");
		}
		for (int i = 0; i < exceptionTypes.length; i++) {
			exceptionBuffer.append(exceptionTypes[i].getName());

			if (i != exceptionTypes.length - 1) {
				exceptionBuffer.append(",");
			}
		}

		//组装方法的参数列表  
		StringBuilder parameterBuffer = new StringBuilder();
		for (int i = 0; i < parameterTypes.length; i++) {
			Class<?> parameterType = parameterTypes[i];

			//动态指定方法参数的变量名  
			parameterBuffer.append(parameterType.getName() + " p" + i);

			if (i != parameterTypes.length - 1) {
				parameterBuffer.append(",");
			}
		}

		//设置返回类型
		int returnType = -1;
		Type rpcMethodReturnType = method.getGenericReturnType(); //rpc远程方法返回类型
		if (methodReturnType.equals(Constant.METHODRETURNTYPE_VOID)) {
			//无返回值
			returnType = Constant.RETURNTYPE_VOID;
		} else {
			if (methodReturnType.equals(Constant.METHODRETURNTYPE_PACKAGE)) {
				//指定封装

				//去除封装，获取子类类型
				Type t = method.getGenericReturnType();
				ParameterizedType p = (ParameterizedType) t;
				Type subType = p.getActualTypeArguments()[0];
				rpcMethodReturnType = subType; //将子类类型设为返回类型

				if (methodReturnType.equals(Constant.METHODRETURNTYPE_STRING)) {
					//返回封装文本
					returnType = Constant.RETURNTYPE_PACKAGE_STRING;
				} else {
					//返回封装非文本
					returnType = Constant.RETURNTYPE_PACKAGE_OBJECT;
				}
			} else {
				//非指定封装
				if (methodReturnType.equals(Constant.METHODRETURNTYPE_STRING)) {
					//返回文本
					returnType = Constant.RETURNTYPE_STRING;
				} else {
					//返回非文本
					returnType = Constant.RETURNTYPE_OBJECT;
				}
			}
		}

		//保存返回值类型
		int methodIdIndexCur = rpcHttpService.methodIdIndex.getAndIncrement();
		//设置返回类型缓存
		rpcHttpService.methodReturnJavaType.add(JsonLIB.getJavaType(rpcMethodReturnType));

		StringBuilder methodDeclare = new StringBuilder();

		//方法声明，由于是实现接口的方法，所以是public 
		methodDeclare.append("public " + methodReturnType + " " + methodName + "(" + parameterBuffer + ")" + exceptionBuffer + " {" + System.getProperty("line.separator"));

		methodDeclare.append("int methodIdIndexCur = " + methodIdIndexCur + ";" + System.getProperty("line.separator"));
		methodDeclare.append("int returnType = " + returnType + ";" + System.getProperty("line.separator"));

		//填充rpcHttpClientConfig参数
		methodDeclare.append("String serviceUrl = \"" + rpcHttpService.rpcHttpServiceInfo.getServiceUrl() + "\";" + System.getProperty("line.separator"));
		methodDeclare.append("String relativeUrl = \"" + methodConfig.getUrl() + "\";" + System.getProperty("line.separator"));
		methodDeclare.append("String bindIpPort = \"" + Tool.getString(rpcHttpService.rpcHttpClient.rpcHttpClientConfig.getBindIpPort(), "") + "\";" + System.getProperty("line.separator"));

		//填充methodConfig参数
		methodDeclare.append("int requestMappingMethod = " + methodConfig.getMethod() + ";" + System.getProperty("line.separator"));
		methodDeclare.append("String targetEncoding = \"" + methodConfig.getTargetEncoding() + "\";" + System.getProperty("line.separator"));
		methodDeclare.append("int connectTimeoutSecond = " + methodConfig.getConnectTimeoutSecond() + ";" + System.getProperty("line.separator"));
		methodDeclare.append("int readTimeoutSecond = " + methodConfig.getReadTimeoutSecond() + ";" + System.getProperty("line.separator"));

		//填充参数名，参数注解类型，参数值
		String dynamicParamDeclare = com.lion.utility.http.rpchttp.entity.DynamicParam.class.getName() + " dynamicParam = null;" + System.getProperty("line.separator");
		StringBuilder paramDeclare = new StringBuilder();
		int total = 0;
		for (int i = 0; i < parameterTypes.length; i++) {
			String paramName = methodConfig.getParamNames().get(i);
			int paramAnnotationType = methodConfig.getParamAnnotationTypes().get(i);
			Class<?> parameterType = parameterTypes[i];

			if (parameterType.getName().equals(DynamicParam.class.getName())) {
				//动态参数
				dynamicParamDeclare = com.lion.utility.http.rpchttp.entity.DynamicParam.class.getName() + " dynamicParam = p" + i + ";" + System.getProperty("line.separator");
			} else {
				//非动态参数
				if (paramAnnotationType == Constant.METHODPARAMANNOTATIONTYPE_REQUESTENTITY) {
					//处理requestentity参数，当做requestparam处理，可以存在多个requestentity，属性若重复，则覆盖处理
					//只支持一层属性，只支持简单数据类型的属性
					for (Field field : parameterType.getDeclaredFields()) {
						paramDeclare.append("paramNames[" + total + "] = \"" + field.getName() + "\";" + System.getProperty("line.separator"));
						paramDeclare.append("paramAnnotationTypes[" + total + "] = " + Constant.METHODPARAMANNOTATIONTYPE_REQUESTPARAM + ";" + System.getProperty("line.separator"));
						paramDeclare.append("params[" + total + "] = p" + i + ".get" + Tool.toUpperFristChar(field.getName()) + "();" + System.getProperty("line.separator"));
						total++;
					}
				} else {
					paramDeclare.append("paramNames[" + total + "] = \"" + paramName + "\";" + System.getProperty("line.separator"));
					paramDeclare.append("paramAnnotationTypes[" + total + "] = " + paramAnnotationType + ";" + System.getProperty("line.separator"));
					paramDeclare.append("params[" + total + "] = " + Tool.inbox(parameterType, "p" + i) + ";" + System.getProperty("line.separator"));
					total++;
				}
			}
		}
		methodDeclare.append("String[] paramNames = new String[" + total + "];" + System.getProperty("line.separator"));
		methodDeclare.append("int[] paramAnnotationTypes = new int[" + total + "];" + System.getProperty("line.separator"));
		methodDeclare.append("Object[] params = new Object[" + total + "];" + System.getProperty("line.separator"));
		methodDeclare.append(paramDeclare);
		methodDeclare.append(dynamicParamDeclare);

		String handlerStr = "this." + BytecodeProxy.rpcHttpServiceVar + ".handler(dynamicParam, serviceUrl, relativeUrl, bindIpPort, requestMappingMethod, targetEncoding, connectTimeoutSecond, readTimeoutSecond, paramNames, paramAnnotationTypes, params, methodIdIndexCur, returnType)";
		if (methodReturnType.equals(Constant.METHODRETURNTYPE_VOID)) {
			//无返回值
			methodDeclare.append(handlerStr + ";" + System.getProperty("line.separator"));
		} else {
			//有返回值
			methodDeclare.append("return " + Tool.unbox(method.getReturnType(), handlerStr) + ";" + System.getProperty("line.separator"));
		}

		methodDeclare.append("}");

		return methodDeclare.toString();
	}

	/**
	 * 获取接口配置
	 * 
	 * @param inferfaceClass
	 *            接口
	 * @param <T>
	 *            泛型
	 * @return 结果
	 */
	private static <T> InterfaceConfig getInterfaceConfig(Class<T> inferfaceClass) {
		InterfaceConfig interfaceConfig = new InterfaceConfig();

		BytecodeProxy.setHttpConfig(interfaceConfig, inferfaceClass.getAnnotations());

		return interfaceConfig;
	}

	/**
	 * 获取方法配置
	 * 
	 * @param method
	 *            方法
	 * @param <T>
	 *            泛型
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	private static <T> MethodConfig getMethodConfig(Method method) throws Exception {
		MethodConfig methodConfig = new MethodConfig();

		BytecodeProxy.setHttpConfig(methodConfig, method.getAnnotations());

		List<String> paramNames = new ArrayList<>();
		List<Integer> paramAnnotationTypes = new ArrayList<>();

		for (Parameter param : method.getParameters()) {
			Annotation[] annotations = param.getAnnotations();
			if (annotations != null && annotations.length > 0) {
				//只允许有一个注解
				Annotation annotation = annotations[0];

				//参数支持@PathVariable/@RequestParam/@RequestBody/@RequestHeader注解
				if (annotation.annotationType().equals(PathVariable.class)) {
					PathVariable pathVariable = (PathVariable) annotation;
					paramNames.add(pathVariable.value());
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_PATHVARIABLE);
				} else if (annotation.annotationType().equals(RequestParam.class)) {
					RequestParam requestParam = (RequestParam) annotation;
					paramNames.add(requestParam.value());
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_REQUESTPARAM);
				} else if (annotation.annotationType().equals(RequestEntity.class)) {
					paramNames.add(Constant.REQUESTENTITY_NAME_DEFAULT);
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_REQUESTENTITY);
				} else if (annotation.annotationType().equals(RequestBody.class)) {
					paramNames.add(Constant.REQUESTBODY_NAME_DEFAULT);
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_REQUESTBODY);
				} else if (annotation.annotationType().equals(RequestHeader.class)) {
					RequestHeader requestHeader = (RequestHeader) annotation;
					paramNames.add(requestHeader.value());
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_REQUESTHEADER);
				}
			} else {
				if (param.getType().getTypeName().equals(DynamicParam.class.getName())) {
					//用于动态参数
					paramNames.add("");
					paramAnnotationTypes.add(Constant.METHODPARAMANNOTATIONTYPE_NONE);
				} else {
					throw new Exception(method.getDeclaringClass().getName() + "." + method.getName() + "." + param.getName() + " must have rpchttp annotation");
				}
			}
		}

		methodConfig.setParamNames(paramNames);
		methodConfig.setParamAnnotationTypes(paramAnnotationTypes);
		return methodConfig;
	}

	/**
	 * 合并方法配置
	 * 
	 * @param interfaceConfig
	 *            接口配置
	 * @param methodConfig
	 *            方法配置
	 * @return 结果
	 */
	private static MethodConfig mergeMethodConfig(InterfaceConfig interfaceConfig, MethodConfig methodConfig) {
		methodConfig.setUrl(interfaceConfig.getUrl() + methodConfig.getUrl());

		if (methodConfig.getMethod() == 0) {
			if (interfaceConfig.getMethod() > 0) {
				methodConfig.setMethod(interfaceConfig.getMethod());
			} else {
				methodConfig.setMethod(Constant.REQUESTMETHOD_GET);
			}
		}

		if (!Tool.checkHaveValue(methodConfig.getTargetEncoding())) {
			if (Tool.checkHaveValue(interfaceConfig.getTargetEncoding())) {
				methodConfig.setTargetEncoding(interfaceConfig.getTargetEncoding());
			} else {
				methodConfig.setTargetEncoding(Constant.ENCODING);
			}
		}

		if (methodConfig.getConnectTimeoutSecond() == 0) {
			if (interfaceConfig.getConnectTimeoutSecond() > 0) {
				methodConfig.setConnectTimeoutSecond(interfaceConfig.getConnectTimeoutSecond());
			} else {
				methodConfig.setConnectTimeoutSecond(Constant.HTTP_CONNECTTIMEOUT_SECOND_DEFAULT);
			}
		}

		if (methodConfig.getReadTimeoutSecond() == 0) {
			if (interfaceConfig.getReadTimeoutSecond() > 0) {
				methodConfig.setReadTimeoutSecond(interfaceConfig.getReadTimeoutSecond());
			} else {
				methodConfig.setReadTimeoutSecond(Constant.HTTP_READTIMEOUT_SECOND_DEFAULT);
			}
		}

		return methodConfig;
	}

	/**
	 * 解析httpconfig注解
	 * 
	 * @param interfaceConfig
	 *            接口配置
	 * @param annotations
	 *            注解数组
	 */
	private static void setHttpConfig(InterfaceConfig interfaceConfig, Annotation[] annotations) {
		if (annotations != null && annotations.length > 0) {
			//只允许有一个@HttpConfig注解
			Annotation annotation = annotations[0];

			if (annotation.annotationType().equals(HttpConfig.class)) {
				HttpConfig httpConfig = (HttpConfig) annotation;

				if (Tool.checkHaveValue(httpConfig.url())) {
					interfaceConfig.setUrl(httpConfig.url());
				}

				if (httpConfig.method() > 0) {
					interfaceConfig.setMethod(httpConfig.method());
				}

				if (Tool.checkHaveValue(httpConfig.targetEncoding())) {
					interfaceConfig.setTargetEncoding(httpConfig.targetEncoding());
				}

				if (httpConfig.connectTimeoutSecond() > 0) {
					interfaceConfig.setConnectTimeoutSecond(httpConfig.connectTimeoutSecond());
				}

				if (httpConfig.readTimeoutSecond() > 0) {
					interfaceConfig.setReadTimeoutSecond(httpConfig.readTimeoutSecond());
				}
			}
		}
	}
}
