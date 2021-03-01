package com.lion.utility.http.rpchttp.entity;

/**
 * 添加用户信息实体
 * 
 * @author lion
 *
 */
public class InputAddUserInfo {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String userpass;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
}