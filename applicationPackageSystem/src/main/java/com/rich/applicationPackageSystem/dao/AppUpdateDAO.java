package com.rich.applicationPackageSystem.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.ClassPathResource;
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
		try(InputStream appUpdateMapJsonStream =  new ClassPathResource("appUpdateMap.json").getInputStream()) {
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
	
	public void updateMapFile(Map<String, AppUpdateBean> updateMap) {
		//构造appUpdateMap.json的结构
		JSONObject updateJson = new JSONObject(new HashMap<String,Object>()) {
			private static final long serialVersionUID = -7011423692609901386L;
			{
				put("updateMapList",updateMap.values());
			}
		};
		//更新文件
		try(FileOutputStream os = new FileOutputStream(ResourceUtils.getFile("classpath:appUpdateMap.json"))) {
			JSON.writeJSONString(os, updateJson);
		} catch (IOException e) {
			log.error("更新appUpdateMap.json文件时报错",e);
		} 
		//刷新appUpdateBeans
		initOrRefreshBeansMap();
	}
}
