package com.lion.utility.http.rpchttp.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JavaType;

import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.tool.log.LogLIB;
import com.lion.utility.http.rpchttp.constant.Constant;
import com.lion.utility.http.rpchttp.entity.DynamicParam;
import com.lion.utility.http.rpchttp.entity.RPCHttpResult;
import com.lion.utility.http.rpchttp.entity.RPCHttpServiceInfo;
import com.lion.utility.http.net.NetLIB;
import com.lion.utility.http.net.entity.NetException;
import com.lion.utility.http.net.entity.NetResult;

/**
 * RPC http客户端服务类
 * 
 * @author lion
 */
public class RPCHttpService {
	/**
	 * rpc http客户端
	 */
	protected RPCHttpClient rpcHttpClient;
	/**
	 * rpc http client注册服务类
	 */
	protected RPCHttpServiceInfo rpcHttpServiceInfo;

	/**
	 * 方法返回值类型列表
	 */
	protected List<JavaType> methodReturnJavaType = new ArrayList<>();
	/**
	 * 方式id索引，用于上述方法返回值类型列表下标
	 */
	protected AtomicInteger methodIdIndex = new AtomicInteger(0);

	/**
	 * 实例化
	 * 
	 * @param rpcHttpClient      rpc http客户端
	 * @param rpcHttpServiceInfo rpc http client注册服务类
	 */
	public RPCHttpService(RPCHttpClient rpcHttpClient, RPCHttpServiceInfo rpcHttpServiceInfo) {
		this.rpcHttpClient = rpcHttpClient;
		this.rpcHttpServiceInfo = rpcHttpServiceInfo;
	}

	/**
	 * 执行rpc（同步返回处理结果）
	 * 
	 * @param dynamicParam         动态参数
	 * @param serviceUrl           服务地址
	 * @param relativeUrl          接口相对地址
	 * @param bindIpPort           绑定ip端口
	 * @param requestMappingMethod 请求方式
	 * @param targetEncoding       目标编码
	 * @param connectTimeoutSecond 链接超时秒数
	 * @param readTimeoutSecond    读取超时秒数
	 * @param paramNames           参数名列表
	 * @param paramAnnotationTypes 参数注解类型列表
	 * @param params               参数值列表
	 * @param methodIdIndexCur     方法索引
	 * @param returnType           返回内容类型
	 * @return 结果
	 * @throws Exception 异常
	 */
	public Object handler(DynamicParam dynamicParam, String serviceUrl, String relativeUrl,
			String bindIpPort, int requestMappingMethod, String targetEncoding,
			int connectTimeoutSecond, int readTimeoutSecond,
			String[] paramNames, int[] paramAnnotationTypes, Object[] params,
			int methodIdIndexCur, int returnType) throws Exception {
		if (paramNames.length != paramAnnotationTypes.length ||
				paramNames.length != params.length) {
			throw new Exception("invalid param length, paramNames:" + paramNames.length + ", paramAnnotationTypes:" + paramAnnotationTypes.length + ", params:" + params.length);
		}

		Map<String, String> headerMap = new HashMap<>();
		Map<String, String> dataMap = new HashMap<>();
		String postData = "";

		int requestParamTotal = 0;
		int requestBodyTotal = 0;

		for (int i = 0; i < paramNames.length; i++) {
			String paramName = paramNames[i];
			int paramAnnotationType = paramAnnotationTypes[i];
			Object param = params[i];

			switch (paramAnnotationType) {
			case Constant.METHODPARAMANNOTATIONTYPE_PATHVARIABLE:
				// 处理路径参数
				relativeUrl = relativeUrl.replace("{" + paramName + "}", param.toString());
				break;
			case Constant.METHODPARAMANNOTATIONTYPE_REQUESTPARAM:
				// 处理requestparm参数，且不能与requestbody参数并存
				dataMap.put(paramName, param.toString());
				requestParamTotal++;
				break;
			case Constant.METHODPARAMANNOTATIONTYPE_REQUESTBODY:
				// 处理requestbody参数，只允许存在一个requestbody，且不能与requestparm参数并存
				postData = JsonLIB.toJson(param);
				requestBodyTotal++;
				break;
			case Constant.METHODPARAMANNOTATIONTYPE_REQUESTHEADER:
				// 处理header参数
				headerMap.put(paramName, param.toString());
				break;
			case Constant.METHODPARAMANNOTATIONTYPE_NONE:
				// 忽略
				break;
			default:
				throw new Exception("invalid paramAnnotationType: " + paramAnnotationType);
			}
		}

		if (requestParamTotal > 0 && requestBodyTotal > 0) {
			throw new Exception("requestParam requestBody can't coexist");
		} else if (requestBodyTotal > 1) {
			throw new Exception("requestBody must only one");
		}

		// 参数处理
		String finalUrl = serviceUrl + relativeUrl;
		String finalBindIpPort = bindIpPort;
		String finalTargetEncoding = targetEncoding;
		int finalConnectTimeoutSecond = connectTimeoutSecond;
		int finalReadTimeoutSecond = readTimeoutSecond;
		// 动态参数处理
		if (dynamicParam != null) {
			if (Tool.checkHaveValue(dynamicParam.getUrl())) {
				finalUrl = dynamicParam.getUrl();
			} else if (Tool.checkHaveValue(dynamicParam.getServiceUrl())) {
				finalUrl = dynamicParam.getServiceUrl() + relativeUrl;
			}

			if (Tool.checkHaveValue(dynamicParam.getBindIpPort())) {
				finalBindIpPort = dynamicParam.getBindIpPort();
			}

			if (Tool.checkHaveValue(dynamicParam.getTargetEncoding())) {
				finalTargetEncoding = dynamicParam.getTargetEncoding();
			}

			if (dynamicParam.getConnectTimeoutSecond() != null) {
				finalConnectTimeoutSecond = dynamicParam.getConnectTimeoutSecond();
			}

			if (dynamicParam.getReadTimeoutSecond() != null) {
				finalConnectTimeoutSecond = dynamicParam.getReadTimeoutSecond();
			}
		}

		// 是否需要返回值
		boolean returnContent = true;
		if (returnType == Constant.RETURNTYPE_VOID) {
			returnContent = false;
		}

		NetResult netResult = null;

		try {
			switch (requestMappingMethod) {
			case Constant.REQUESTMETHOD_GET: {
				netResult = NetLIB.getFileContentGet(
						finalUrl,
						dataMap,
						headerMap,
						finalTargetEncoding,
						finalConnectTimeoutSecond * 1000,
						finalReadTimeoutSecond * 1000,
						returnContent,
						200,
						finalBindIpPort);
			}
				break;
			case Constant.REQUESTMETHOD_POST: {
				if (Tool.checkHaveValue(postData)) {
					headerMap.put("Content-Type", "application/json");
					netResult = NetLIB.getFileContentPost(
							finalUrl,
							null,
							postData,
							headerMap,
							finalTargetEncoding,
							finalConnectTimeoutSecond * 1000,
							finalReadTimeoutSecond * 1000,
							returnContent,
							200,
							finalBindIpPort);
				} else {
					headerMap.put("Content-Type", "application/x-www-form-urlencoded");
					netResult = NetLIB.getFileContentPost(
							finalUrl,
							null,
							dataMap,
							headerMap,
							finalTargetEncoding,
							finalConnectTimeoutSecond * 1000,
							finalReadTimeoutSecond * 1000,
							returnContent,
							200,
							finalBindIpPort);
				}
			}
				break;
			default:
				throw new Exception("invalid requestMappingMethod: " + requestMappingMethod);
			}

			switch (returnType) {
			case Constant.RETURNTYPE_VOID: {
				return null;
			}
			case Constant.RETURNTYPE_STRING: {
				return netResult.getOutInfo();
			}
			case Constant.RETURNTYPE_PACKAGE_STRING: {
				RPCHttpResult<String> rpcHttpResult = new RPCHttpResult<>();
				rpcHttpResult.setNetResult(netResult);
				rpcHttpResult.setResult(netResult.getOutInfo());
				return rpcHttpResult;
			}
			case Constant.RETURNTYPE_OBJECT: {
				return JsonLIB.fromJson(netResult.getOutInfo(), this.methodReturnJavaType.get(methodIdIndexCur));
			}
			case Constant.RETURNTYPE_PACKAGE_OBJECT: {
				RPCHttpResult<Object> rpcHttpResult = new RPCHttpResult<>();
				rpcHttpResult.setNetResult(netResult);
				rpcHttpResult.setResult(JsonLIB.fromJson(netResult.getOutInfo(), this.methodReturnJavaType.get(methodIdIndexCur)));
				return rpcHttpResult;
			}
			default:
				throw new Exception("invalid returnType: " + returnType);
			}
		} catch (NetException e) {
			netResult = e.getNetResult();
			LogLIB.error("rpcHttp handler exception", e);
			throw e;
		} finally {
			if (netResult != null) {
				if (this.rpcHttpClient.rpcHttpClientConfig.getLogLevel() == Constant.LOGLEVEL_INOUTERROR) {
					LogLIB.rpcHttp(netResult.toString());
				} else if (this.rpcHttpClient.rpcHttpClientConfig.getLogLevel() == Constant.LOGLEVEL_INERROR) {
					LogLIB.rpcHttp(netResult.toInString());
				}
			}
		}
	}
}
