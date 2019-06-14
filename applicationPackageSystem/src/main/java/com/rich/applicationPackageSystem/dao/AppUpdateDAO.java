package com.rich.applicationPackageSystem.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class AppUpdateDAO {

	/**
	 * 存放appUpdateMap.json中所有appUpdateBean 用其id字段作为key
	 */
	private static Map<String, AppUpdateBean> appUpdateBeans = new ConcurrentHashMap<>();
	
	/**
	 * appUpdateBeans初始化过得标志位
	 */
	private static boolean isInited = false;
	
	public Map<String, AppUpdateBean> getAppUpdateBeans(){
		if(isInited) return appUpdateBeans;
		else return initOrRefreshBeansMap();
	}
	
	private Map<String, AppUpdateBean> initOrRefreshBeansMap() {
		try(FileInputStream appUpdateMapJsonStream = new FileInputStream(ResourceUtils.getFile("classpath:appUpdateMap.json"))) {
			JSONObject json = JSON.parseObject(appUpdateMapJsonStream, JSONObject.class);
			JSONArray jsonArray = json.getJSONArray("updateMapList");
			for (Object obj : jsonArray) {
				 AppUpdateBean app = ((JSONObject)obj).toJavaObject(AppUpdateBean.class);
				appUpdateBeans.put(app.getId(),app);
			}
			isInited = true;
			return appUpdateBeans;
		} catch (IOException e) {
			log.error("初始化BeansMap时报错",e);
			isInited = false;
			return null;
		}
	}
}
