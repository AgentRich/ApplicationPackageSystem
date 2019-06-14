package com.rich.applicationPackageSystem.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.rich.applicationPackageSystem.dao.AppUpdateDAO;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;

@Service
public class AppUpdateService {
	@Autowired
	private AppUpdateDAO appUpdateDAO;

	public Collection<AppUpdateBean> allUpdateMap() throws IOException {
		Map<String, AppUpdateBean> appUpdateBeans = appUpdateDAO.getAppUpdateBeans();
		if(Objects.isNull(appUpdateBeans)) return null;
		else return appUpdateBeans.values();
	}

	
	public void allUpdateInfo2Map(AppUpdateBean appUpdate) {
		String appUpdateJson = JSON.toJSONString(appUpdate);
		
	}
	
	
	
}
