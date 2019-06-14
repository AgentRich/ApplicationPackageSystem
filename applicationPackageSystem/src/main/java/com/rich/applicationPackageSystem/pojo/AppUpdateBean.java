package com.rich.applicationPackageSystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class AppUpdateBean {
	/** id */
	private String id;
	
	/** 软件名称 */
	@EqualsAndHashCode.Exclude
	private String name;
	
	/** 软件更新url，此url用于指定软件下载的网站，来获取真正的下载地址 */
	@EqualsAndHashCode.Exclude
	private String updateUrl;
	
	/** 软件真实的下载地址 */
	@EqualsAndHashCode.Exclude
	private String downUrl;
	
	/** 软件信息的更新时间 */
	@EqualsAndHashCode.Exclude
	private String updateTime;
	
	
}
