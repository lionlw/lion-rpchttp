package com.lion.utility.http.rpchttp.entity;

import java.util.List;

/**
 * 方法配置
 * 
 * @author lion
 *
 */
public class MethodConfig extends InterfaceConfig {
	/**
	 * 参数名列表
	 */
	private List<String> paramNames;
	/**
	 * 参数注解类型列表
	 */
	private List<Integer> paramAnnotationTypes;

	/**
	 * 设置参数名列表
	 * 
	 * @param paramNames
	 *            参数名列表
	 */
	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}

	/**
	 * 获取参数名列表
	 * 
	 * @return 参数名列表
	 */
	public List<String> getParamNames() {
		return paramNames;
	}

	/**
	 * 设置参数注解类型列表
	 * 
	 * @param paramAnnotationTypes
	 *            参数注解类型列表
	 */
	public void setParamAnnotationTypes(List<Integer> paramAnnotationTypes) {
		this.paramAnnotationTypes = paramAnnotationTypes;
	}

	/**
	 * 获取参数注解类型列表
	 * 
	 * @return 参数注解类型列表
	 */
	public List<Integer> getParamAnnotationTypes() {
		return paramAnnotationTypes;
	}

}
