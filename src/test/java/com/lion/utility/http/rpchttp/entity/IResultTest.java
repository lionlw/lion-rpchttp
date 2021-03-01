package com.lion.utility.http.rpchttp.entity;

import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.tool.log.LogLIB;

/**
 * 接口返回实体
 * 
 * @author lion
 * 
 * @param <T> 泛型
 */
public class IResultTest<T> {
	/**
	 * 返回码
	 */
	private Integer code = 0;
	/**
	 * 返回信息
	 */
	private String msg = "";
	/**
	 * 返回实体
	 */
	private T data;

	/**
	 * 设置返回码
	 * 
	 * @param code 返回码
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 获取返回码
	 * 
	 * @return 返回码
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 设置返回信息
	 * 
	 * @param msg 返回信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取返回信息
	 * 
	 * @return 返回信息
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置返回实体
	 * 
	 * @param data 返回实体
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 获取返回实体
	 * 
	 * @return 返回实体
	 */
	public T getData() {
		return data;
	}

	/**
	 * 输出结果信息
	 * 
	 * @return 结果
	 */
	public String toResultString() {
		return "IResult [code=" + code + ", msg=" + msg + "]";
	}

	@Override
	public String toString() {
		try {
			return JsonLIB.toJson(this);
		} catch (Exception e) {
			LogLIB.error("", e);
		}

		return "";
	}
}
