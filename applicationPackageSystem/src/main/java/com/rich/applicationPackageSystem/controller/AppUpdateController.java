package com.rich.applicationPackageSystem.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.rich.applicationPackageSystem.common.Response;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;
import com.rich.applicationPackageSystem.service.AppUpdateService;

@RestController
public class AppUpdateController {
	
	@Autowired
	private AppUpdateService appuodateservice;
	
	/**
	 * 向软件更新列表中添加软件更新的信息
	 */
	@PutMapping("/addUpdateApp2Map")
	public JSONObject addUpdateApp2Map(AppUpdateBean appUpdate) {
		String id = appUpdate.getId();
		
		return null;
	}
	
	@PostMapping("/updateAppInfo2Map")
	public Response updateAppInfo2Map(AppUpdateBean appUpdate) {
		appuodateservice.allUpdateInfo2Map(appUpdate);
		return new Response("200","successful",null);
	}
	/**
	 * 列出所有可更新的软件列表
	 * @return 软件列表数据
	 * @throws IOException 
	 */
	@RequestMapping("/listUpdateMap")
	public Response listUpdateMap() throws IOException {
		Collection<AppUpdateBean> allUpdateMap = appuodateservice.allUpdateMap();
		if(Objects.isNull(allUpdateMap)) return new Response("500","bad",null);
		else return new Response("200","successful",allUpdateMap);
	}
	
	
}
