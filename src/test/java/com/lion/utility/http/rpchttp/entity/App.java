package com.lion.utility.http.rpchttp.entity; 
 
/** 
 * 应用表 
 */ 
public class App { 
	/** 
	 * 应用id 
	 */ 
	private Integer appId; 
	/** 
	 * 应用标识代码（全局唯一） 
	 */ 
	private String appCode; 
	/** 
	 * 应用名称 
	 */ 
	private String appName; 
	/** 
	 * 应用类别id 
	 */ 
	private Integer appTypeId; 
	/** 
	 * 开发语言（1：java，2：python） 
	 */ 
	private Integer devLanguage; 
	/** 
	 * 开发者 
	 */ 
	private String developer; 
	/** 
	 * 报警邮件组id 
	 */ 
	private Integer alarmEmailGroupId; 
	/** 
	 * 备注 
	 */ 
	private String brief; 
	/** 
	 * 启用状态（0：关闭，1：开启） 
	 */ 
	private Integer status; 
	/** 
	 * 创建时间 
	 */ 
	private Long createTime; 
	/** 
	 * 更新时间 
	 */ 
	private Long updateTime; 
	/** 
	 * 更新人用户名 
	 */ 
	private String updateUserName; 
	 
	/** 
	 * 获取应用id  
	 */ 
	public Integer getAppId() { 
		return appId; 
	} 
	 
	/** 
	 * 设置应用id  
	 */ 
	public void setAppId(Integer appId) { 
		this.appId = appId; 
	} 
	 
	/** 
	 * 获取应用标识代码（全局唯一）  
	 */ 
	public String getAppCode() { 
		return appCode; 
	} 
	 
	/** 
	 * 设置应用标识代码（全局唯一）  
	 */ 
	public void setAppCode(String appCode) { 
		this.appCode = appCode; 
	} 
	 
	/** 
	 * 获取应用名称  
	 */ 
	public String getAppName() { 
		return appName; 
	} 
	 
	/** 
	 * 设置应用名称  
	 */ 
	public void setAppName(String appName) { 
		this.appName = appName; 
	} 
	 
	/** 
	 * 获取应用类别id  
	 */ 
	public Integer getAppTypeId() { 
		return appTypeId; 
	} 
	 
	/** 
	 * 设置应用类别id  
	 */ 
	public void setAppTypeId(Integer appTypeId) { 
		this.appTypeId = appTypeId; 
	} 
	 
	/** 
	 * 获取开发语言（1：java，2：python）  
	 */ 
	public Integer getDevLanguage() { 
		return devLanguage; 
	} 
	 
	/** 
	 * 设置开发语言（1：java，2：python）  
	 */ 
	public void setDevLanguage(Integer devLanguage) { 
		this.devLanguage = devLanguage; 
	} 
	 
	/** 
	 * 获取开发者  
	 */ 
	public String getDeveloper() { 
		return developer; 
	} 
	 
	/** 
	 * 设置开发者  
	 */ 
	public void setDeveloper(String developer) { 
		this.developer = developer; 
	} 
	 
	/** 
	 * 获取报警邮件组id  
	 */ 
	public Integer getAlarmEmailGroupId() { 
		return alarmEmailGroupId; 
	} 
	 
	/** 
	 * 设置报警邮件组id  
	 */ 
	public void setAlarmEmailGroupId(Integer alarmEmailGroupId) { 
		this.alarmEmailGroupId = alarmEmailGroupId; 
	} 
	 
	/** 
	 * 获取备注  
	 */ 
	public String getBrief() { 
		return brief; 
	} 
	 
	/** 
	 * 设置备注  
	 */ 
	public void setBrief(String brief) { 
		this.brief = brief; 
	} 
	 
	/** 
	 * 获取启用状态（0：关闭，1：开启）  
	 */ 
	public Integer getStatus() { 
		return status; 
	} 
	 
	/** 
	 * 设置启用状态（0：关闭，1：开启）  
	 */ 
	public void setStatus(Integer status) { 
		this.status = status; 
	} 
	 
	/** 
	 * 获取创建时间  
	 */ 
	public Long getCreateTime() { 
		return createTime; 
	} 
	 
	/** 
	 * 设置创建时间  
	 */ 
	public void setCreateTime(Long createTime) { 
		this.createTime = createTime; 
	} 
	 
	/** 
	 * 获取更新时间  
	 */ 
	public Long getUpdateTime() { 
		return updateTime; 
	} 
	 
	/** 
	 * 设置更新时间  
	 */ 
	public void setUpdateTime(Long updateTime) { 
		this.updateTime = updateTime; 
	} 
	 
	/** 
	 * 获取更新人用户名  
	 */ 
	public String getUpdateUserName() { 
		return updateUserName; 
	} 
	 
	/** 
	 * 设置更新人用户名  
	 */ 
	public void setUpdateUserName(String updateUserName) { 
		this.updateUserName = updateUserName; 
	} 
	
	//------------------------
	/**
	 * 报警邮件列表（半角,间隔）
	 */
	private String alarmEmails;

	public String getAlarmEmails() {
		return alarmEmails;
	}

	public void setAlarmEmails(String alarmEmails) {
		this.alarmEmails = alarmEmails;
	}
	 
} 
 
