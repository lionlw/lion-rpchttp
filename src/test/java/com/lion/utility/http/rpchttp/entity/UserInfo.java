package com.lion.utility.http.rpchttp.entity;

/**
 * 用户实体
 * 
 * @author lion
 *
 */
public class UserInfo {
	/**
	 * 用户id
	 */
	private Integer userid;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String userpass;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

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