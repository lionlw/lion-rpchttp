package com.lion.utility.http.rpchttp.entity;

import com.lion.utility.http.rpchttp.constant.Constant;

/**
 * rpc http client配置
 * 
 * @author lion
 *
 */
public class RPCHttpClientConfig {
	/**
	 * 绑定ip端口，为空则表示不使用（127.0.0.1:8080）
	 */
	private String bindIpPort;
	/**
	 * 是否debug模式
	 */
	private Boolean isDebug = false;
	/**
	 * 日志输出级别
	 */
	private Integer logLevel = Constant.LOGLEVEL_ERROR;
	/**
	 * 是否mock server数据（用于当server无法连接时，模拟server数据返回）
	 */
	private Boolean isMock = false;

	/**
	 * 设置绑定ip端口，为空则表示不使用（127.0.0.1:8080）
	 * 
	 * @param bindIpPort
	 *            绑定ip端口，为空则表示不使用（127.0.0.1:8080）
	 */
	public void setBindIpPort(String bindIpPort) {
		this.bindIpPort = bindIpPort;
	}

	/**
	 * 获取绑定ip端口，为空则表示不使用（127.0.0.1:8080）
	 * 
	 * @return 绑定ip端口，为空则表示不使用（127.0.0.1:8080）
	 */
	public String getBindIpPort() {
		return bindIpPort;
	}

	/**
	 * 设置是否debug模式
	 * 
	 * @param isDebug
	 *            是否debug模式
	 */
	public void setIsDebug(Boolean isDebug) {
		this.isDebug = isDebug;
	}

	/**
	 * 获取是否debug模式
	 * 
	 * @return 是否debug模式
	 */
	public Boolean getIsDebug() {
		return isDebug;
	}

	/**
	 * 设置日志输出级别
	 * 
	 * @param logLevel
	 *            日志输出级别
	 */
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * 获取日志输出级别
	 * 
	 * @return 日志输出级别
	 */
	public Integer getLogLevel() {
		return logLevel;
	}

	/**
	 * 设置是否mock server数据（用于当server无法连接时，模拟server数据返回）
	 * 
	 * @param isMock
	 *            是否mock server数据（用于当server无法连接时，模拟server数据返回）
	 */
	public void setIsMock(Boolean isMock) {
		this.isMock = isMock;
	}

	/**
	 * 获取是否mock server数据（用于当server无法连接时，模拟server数据返回）
	 * 
	 * @return 是否mock server数据（用于当server无法连接时，模拟server数据返回）
	 */
	public Boolean getIsMock() {
		return isMock;
	}

}
