package com.lion.utility.http.rpchttp.entity;

/**
 * 动态参数，用于满足rpchttp需动态传入参数的场景
 * 
 * @author lion
 *
 */
public class DynamicParam {
	/**
	 * url完整地址
	 */
	private String url;
	/**
	 * 服务地址（用于替换RPCHttpClient实例化时的serviceUrl）
	 */
	private String serviceUrl;
	/**
	 * 绑定ip端口（127.0.0.1:8080）
	 */
	private String bindIpPort;
	/**
	 * 目标编码
	 */
	private String targetEncoding;
	/**
	 * 链接超时秒数
	 */
	private Integer connectTimeoutSecond;
	/**
	 * 读取超时秒数
	 */
	private Integer readTimeoutSecond;

	/**
	 * 设置url完整地址
	 * 
	 * @param url
	 *            url完整地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取url完整地址
	 * 
	 * @return url完整地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置服务地址（用于替换RPCHttpClient实例化时的serviceUrl）
	 * 
	 * @param serviceUrl
	 *            服务地址（用于替换RPCHttpClient实例化时的serviceUrl）
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * 获取服务地址（用于替换RPCHttpClient实例化时的serviceUrl）
	 * 
	 * @return 服务地址（用于替换RPCHttpClient实例化时的serviceUrl）
	 */
	public String getServiceUrl() {
		return serviceUrl;
	}

	/**
	 * 设置绑定ip端口（127.0.0.1:8080）
	 * 
	 * @param bindIpPort
	 *            绑定ip端口（127.0.0.1:8080）
	 */
	public void setBindIpPort(String bindIpPort) {
		this.bindIpPort = bindIpPort;
	}

	/**
	 * 获取绑定ip端口（127.0.0.1:8080）
	 * 
	 * @return 绑定ip端口（127.0.0.1:8080）
	 */
	public String getBindIpPort() {
		return bindIpPort;
	}

	/**
	 * 设置目标编码
	 * 
	 * @param targetEncoding
	 *            目标编码
	 */
	public void setTargetEncoding(String targetEncoding) {
		this.targetEncoding = targetEncoding;
	}

	/**
	 * 获取目标编码
	 * 
	 * @return 目标编码
	 */
	public String getTargetEncoding() {
		return targetEncoding;
	}

	/**
	 * 设置链接超时秒数
	 * 
	 * @param connectTimeoutSecond
	 *            链接超时秒数
	 */
	public void setConnectTimeoutSecond(Integer connectTimeoutSecond) {
		this.connectTimeoutSecond = connectTimeoutSecond;
	}

	/**
	 * 获取链接超时秒数
	 * 
	 * @return 链接超时秒数
	 */
	public Integer getConnectTimeoutSecond() {
		return connectTimeoutSecond;
	}

	/**
	 * 设置读取超时秒数
	 * 
	 * @param readTimeoutSecond
	 *            读取超时秒数
	 */
	public void setReadTimeoutSecond(Integer readTimeoutSecond) {
		this.readTimeoutSecond = readTimeoutSecond;
	}

	/**
	 * 获取读取超时秒数
	 * 
	 * @return 读取超时秒数
	 */
	public Integer getReadTimeoutSecond() {
		return readTimeoutSecond;
	}

}
