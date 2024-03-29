package com.rich.applicationPackageSystem.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rich.applicationPackageSystem.common.Response;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;
import com.rich.applicationPackageSystem.service.AppDownloadService;
import com.rich.applicationPackageSystem.service.AppUpdateService;

@RestController
public class AppUpdateController {
	
	@Autowired
	private AppUpdateService appUpdateService;
	
	@Autowired
	private AppDownloadService appDownloadService;
	
	
	@PostMapping("updateAppInfo2Map")
	public Response updateAppInfo2Map(AppUpdateBean appUpdate,String removeId) {
		appUpdateService.updateInfo2Map(appUpdate,removeId);
		return new Response("200","successful",null);
	}
	/**
	 * 列出所有可更新的软件列表
	 * @return 软件列表数据
	 * @throws IOException 
	 */
	@RequestMapping("listUpdateMap")
	public Response listUpdateMap() throws IOException {
		Collection<AppUpdateBean> allUpdateMap = appUpdateService.allUpdateMap();
		if(Objects.isNull(allUpdateMap)) return new Response("500","bad",null);
		else return new Response("200","successful",allUpdateMap);
	}
	
	@PostMapping("updateAppPack")
	public Response updateAppPack(AppUpdateBean appUpdate) {
		// 更新信息
		appUpdateService.updateInfo2Map(appUpdate,null);
		// TODO jsoup 获取软件更新且下载到本地
		appDownloadService.updateAppPack(appUpdate.getDownUrl());
		return new Response("200", "successful", null);
	}
	
	
}
