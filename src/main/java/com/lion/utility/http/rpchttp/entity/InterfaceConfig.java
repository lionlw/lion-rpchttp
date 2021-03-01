package com.lion.utility.http.rpchttp.entity;

/**
 * 接口配置
 * 
 * @author lion
 *
 */
public class InterfaceConfig {
	/**
	 * 地址
	 */
	private String url = "";
	/**
	 * 请求方式
	 */
	private Integer method = 0;
	/**
	 * 目标编码
	 */
	private String targetEncoding = "";
	/**
	 * 链接超时秒数
	 */
	private Integer connectTimeoutSecond = 0;
	/**
	 * 读取超时秒数
	 */
	private Integer readTimeoutSecond = 0;

	/**
	 * 设置地址
	 * 
	 * @param url
	 *            地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置请求方式
	 * 
	 * @param method
	 *            请求方式
	 */
	public void setMethod(Integer method) {
		this.method = method;
	}

	/**
	 * 获取请求方式
	 * 
	 * @return 请求方式
	 */
	public Integer getMethod() {
		return method;
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
