package com.lion.utility.http.rpchttp.entity;

import com.lion.utility.http.net.entity.NetResult;

/**
 * rpchttp返回类（用于保存网络请求明细）
 * 
 * @author lion
 *
 */
public class RPCHttpResult<T> {
	/**
	 * 网络操作返回类
	 */
	private NetResult netResult;
	/**
	 * 请求结果
	 */
	private T result;

	/**
	 * 设置网络操作返回类
	 * 
	 * @param netResult
	 *            网络操作返回类
	 */
	public void setNetResult(NetResult netResult) {
		this.netResult = netResult;
	}

	/**
	 * 获取网络操作返回类
	 * 
	 * @return 网络操作返回类
	 */
	public NetResult getNetResult() {
		return netResult;
	}

	/**
	 * 设置请求结果
	 * 
	 * @param result
	 *            请求结果
	 */
	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * 获取请求结果
	 * 
	 * @return 请求结果
	 */
	public T getResult() {
		return result;
	}

}
