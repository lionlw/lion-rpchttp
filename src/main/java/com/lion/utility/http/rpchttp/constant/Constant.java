package com.lion.utility.http.rpchttp.constant;

/**
 * 常量
 * 
 * @author lion
 */
public class Constant {
	/**
	 * 编码
	 */
	public final static String ENCODING = "UTF-8";

	/**
	 * 链接超时默认秒数
	 */
	public final static int HTTP_CONNECTTIMEOUT_SECOND_DEFAULT = 3;
	/**
	 * 读取超时默认秒数
	 */
	public final static int HTTP_READTIMEOUT_SECOND_DEFAULT = 10;

	/**
	 * 日志输出等级-输入，输出，异常
	 */
	public final static int LOGLEVEL_INOUTERROR = 1;
	/**
	 * 日志输出等级-输入，异常
	 */
	public final static int LOGLEVEL_INERROR = 2;
	/**
	 * 日志输出等级-异常
	 */
	public final static int LOGLEVEL_ERROR = 3;

	/**
	 * 实体（转换为post） 默认名（用于区分）
	 */
	public final static String REQUESTENTITY_NAME_DEFAULT = "_requestEntity_name_default_";
	/**
	 * requestBody 默认名（用于区分）
	 */
	public final static String REQUESTBODY_NAME_DEFAULT = "_requestBody_name_default_";

	/**
	 * requestMethod-get
	 */
	public final static int REQUESTMETHOD_GET = 1;
	/**
	 * requestMethod-post
	 */
	public final static int REQUESTMETHOD_POST = 2;

	/**
	 * MethodParamAnnotationType-无注解
	 */
	public final static int METHODPARAMANNOTATIONTYPE_NONE = -1;
	/**
	 * MethodParamAnnotationType-PathVariable
	 */
	public final static int METHODPARAMANNOTATIONTYPE_PATHVARIABLE = 1;
	/**
	 * MethodParamAnnotationType-RequestParam
	 */
	public final static int METHODPARAMANNOTATIONTYPE_REQUESTPARAM = 2;
	/**
	 * MethodParamAnnotationType-RequestEntity
	 */
	public final static int METHODPARAMANNOTATIONTYPE_REQUESTENTITY = 3;
	/**
	 * MethodParamAnnotationType-RequestBody
	 */
	public final static int METHODPARAMANNOTATIONTYPE_REQUESTBODY = 4;
	/**
	 * MethodParamAnnotationType-RequestHeader
	 */
	public final static int METHODPARAMANNOTATIONTYPE_REQUESTHEADER = 5;

	/**
	 * 返回内容类型-无返回
	 */
	public final static int RETURNTYPE_VOID = 1;
	/**
	 * 返回内容类型-文本
	 */
	public final static int RETURNTYPE_STRING = 2;
	/**
	 * 返回内容类型-封装文本
	 */
	public final static int RETURNTYPE_PACKAGE_STRING = 3;
	/**
	 * 返回内容类型-object
	 */
	public final static int RETURNTYPE_OBJECT = 4;
	/**
	 * 返回内容类型-封装object
	 */
	public final static int RETURNTYPE_PACKAGE_OBJECT = 5;

	/**
	 * 方法无返回值
	 */
	public final static String METHODRETURNTYPE_VOID = "void";
	/**
	 * 方法返回值为string
	 */
	public final static String METHODRETURNTYPE_STRING = "java.lang.String";
	/**
	 * 方法返回值为指定封装
	 */
	public final static String METHODRETURNTYPE_PACKAGE = com.lion.utility.http.rpchttp.entity.RPCHttpResult.class.getName();
}
