package com.rich.applicationPackageSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 下载应用包的服务类
 * @author agent_rich
 *
 */
@Service
public class AppDownloadService {
	@Async
	public void updateAppPack(String address) {
		// 分析真实下载地址
		List<String> realDownloadUrls = analyzeRealDownloadUrl(address);
		// 下载应用包
		downloadAppPack(realDownloadUrls);
	}

	/**
	 *  利用jsoup分析真实的下载地址
	 * @param address 软件下载页面的地址
	 * @return 真实下载地址
	 */
	private List<String> analyzeRealDownloadUrl(String address) {
		ArrayList<String> realDownloadUrls = new ArrayList<String>();
		// 分析保存所有可能的下载地址
		return realDownloadUrls;
	}

	private void downloadAppPack(List<String> realDownloadUrls) {
		realDownloadUrls.forEach(durl -> {
			// http下载应用
		});
	}
}
