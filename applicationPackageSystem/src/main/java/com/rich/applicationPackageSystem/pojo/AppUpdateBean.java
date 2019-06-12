package com.rich.applicationPackageSystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUpdateBean {
	/** id */
	private String id;
	
	/** 软件名称 */
	private String name;
	
	/** 软件更新url，此url用于指定软件下载的网站，来获取真正的下载地址 */
	private String updateUrl;
	
	/** 软件真实的下载地址 */
	private String downUrl;
	
	/** 软件信息的更新时间 */
	private String updateTime;
}
