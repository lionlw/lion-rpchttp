package com.lion.utility.http.rpchttp.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * rpc client注册服务类
 * 
 * @author lion
 */
public class RPCHttpServiceInfo {
	/**
	 * 服务地址
	 */
	private String serviceUrl;
	/**
	 * 注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 */
	private Map<Class<?>, Class<?>> registerServices;

	/**
	 * 构造方法
	 */
	public RPCHttpServiceInfo() {
	}

	/**
	 * 构造方法
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param registerServices
	 *            注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 */
	public RPCHttpServiceInfo(String serviceUrl, Map<Class<?>, Class<?>> registerServices) {
		this.serviceUrl = serviceUrl;
		this.registerServices = registerServices;
	}

	/**
	 * 构造方法（用于简化构造）
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param registerService
	 *            注册服务接口类
	 */
	public RPCHttpServiceInfo(String serviceUrl, Class<?> registerService) {
		this.serviceUrl = serviceUrl;

		this.registerServices = new HashMap<>();
		this.registerServices.put(registerService, null);
	}

	/**
	 * 设置服务地址
	 * 
	 * @param serviceUrl
	 *            服务地址
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * 获取服务地址
	 * 
	 * @return 服务地址
	 */
	public String getServiceUrl() {
		return serviceUrl;
	}

	/**
	 * 设置注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 * 
	 * @param registerServices
	 *            注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 */
	public void setRegisterServices(Map<Class<?>, Class<?>> registerServices) {
		this.registerServices = registerServices;
	}

	/**
	 * 获取注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 * 
	 * @return 注册服务列表（key：接口类，value：client自己实现的继承接口类实现的mock数据类）
	 */
	public Map<Class<?>, Class<?>> getRegisterServices() {
		return registerServices;
	}

}
