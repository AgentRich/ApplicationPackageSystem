package com.rich.applicationPackageSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;

@Service
public class AppUpdateService {

	public JSONObject allUpdateMap() throws IOException {
		File updateMapJsonFile = ResourceUtils.getFile("classpath:appUpdateMap.json");
		JSONObject json = JSON.parseObject(new FileInputStream(updateMapJsonFile), JSONObject.class);
		return json;
	}

	
	public void allUpdateInfo2Map(AppUpdateBean appUpdate) {
		String appUpdateJson = JSON.toJSONString(appUpdate);
		
	}
	
	
	
}
