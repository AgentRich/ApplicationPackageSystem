package com.rich.applicationPackageSystem.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rich.applicationPackageSystem.dao.AppUpdateDAO;
import com.rich.applicationPackageSystem.pojo.AppUpdateBean;

@Service
public class AppUpdateService {
	@Autowired
	private AppUpdateDAO appUpdateDAO;

	public Collection<AppUpdateBean> allUpdateMap() throws IOException {
		Map<String, AppUpdateBean> appUpdateBeans = appUpdateDAO.getAppUpdateBeans();
		return Objects.isNull(appUpdateBeans)?null:appUpdateBeans.values();
	}
	
	public void updateInfo2Map(AppUpdateBean appUpdate, String removeId) {
		Map<String, AppUpdateBean> appUpdateBeans = appUpdateDAO.getAppUpdateBeans();
		if(StringUtils.isEmpty(removeId)) appUpdateBeans.put(appUpdate.getId(),appUpdate);
		else appUpdateBeans.remove(removeId);
		appUpdateDAO.updateMapFile(appUpdateBeans);
	}
	
	
}
