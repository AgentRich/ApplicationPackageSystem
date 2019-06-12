package com.rich.applicationPackageSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class AppUpdateService {

	public JSONObject allUpdateMap() throws IOException {
		File updateMapJsonFile = ResourceUtils.getFile("classpath:appUpdateMap.json");
		JSONObject json = JSON.parseObject(new FileInputStream(updateMapJsonFile), JSONObject.class);
		System.out.println(json);
		return json;
	}
	
	
	
}
