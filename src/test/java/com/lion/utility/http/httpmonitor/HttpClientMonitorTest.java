package com.lion.utility.http.httpmonitor;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.http.httpmonitor.client.HttpClientMonitor;
import com.lion.utility.http.httpmonitor.entity.HttpClientMonitorPolicy;
import com.lion.utility.http.httpmonitor.entity.HttpStat;
import com.lion.utility.http.net.NetLIB;
import com.lion.utility.http.net.entity.NetResult;

public class HttpClientMonitorTest {

	public static void main(String[] args) throws Exception {
		Map<String, String> urlMatcherMap = new HashMap<>();
		urlMatcherMap.put("/aaa/vvv.do", "/aaa/vvv.do");
		urlMatcherMap.put("/v*_*/*/*/api/tv/banner/**", "/api/tv/banner/");

		HttpClientMonitor httpClientMonitor = new HttpClientMonitor("testhttp1", "http://test.admin.tvxio.com:8080/RPCManage/", urlMatcherMap, true);
		NetLIB.httpClientMonitorPolicy = new HttpClientMonitorPolicy(true, httpClientMonitor);

		List<String> urList = new ArrayList<>();
		urList.add("http://sky.tvxio.com/v3_0/SKY2/tou0/api/tv/banner/430/3");
		urList.add("http://sky.tvxio.com/v3_0/SKY2/tou0/api/tv/banner/430/3");
		urList.add("http://sky.tvxio.com/v3_0/SKY2/tou0/api/tv/item/430/3");
		urList.add("http://sky.tvxio.com/v3_0/SKY2/tou0/api/tv/item/430/3");
		urList.add("http://sky.tvxio.com/aaa/vvv.do");
		urList.add("http://sky.tvxio.com/aaa/vvv.do");

		for (String url : urList) {
			try {
				NetResult netResult = NetLIB.getFileContentGet(
						url,
						new HashMap<>(),
						new HashMap<>(),
						Tool.ENCODING,
						3000,
						6000,
						true,
						200,
						null);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
