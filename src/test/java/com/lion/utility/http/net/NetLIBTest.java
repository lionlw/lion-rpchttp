package com.lion.utility.http.net;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.lion.utility.http.net.NetLIB;
import com.lion.utility.http.net.entity.NetResult;
import com.lion.utility.tool.common.Tool;

public class NetLIBTest {
	@Test
	public void setIpDestDomainMap() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		map.put("www.baidu.com", 80);
		NetLIB.setIpDestDomainMap(map);
	}

	@Test
	public void getIp() throws Exception {
		String ip = NetLIB.getIp();
		System.out.println("getIp: " + ip);

		Assert.assertEquals(ip, "172.16.1.11");
	}

	@Test
	public void downloadFile() throws Exception {
		NetLIB.downloadFile("https://www.baidu.com/", "d:\\work\\code\\temp\\log\\baidu.html", 3000, 6000);
	}

	@Test
	public void uploadFile() throws Exception {
		// 无单元测试
	}

	@Test
	public void getFileContentPost1() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> postDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentPost(
				"http://www.baidu.com",
				getDataMap,
				postDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null);
		System.out.println("getFileContentPost1: " + netResult.toString());
	}

	@Test
	public void getFileContentPost2() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentPost(
				"http://www.baidu.com",
				getDataMap,
				"",
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null);
		System.out.println("getFileContentPost2: " + netResult.toString());
	}

	@Test
	public void getFileContentGet() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentGet(
				"http://www.baidu.com",
				getDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null);
		System.out.println("getFileContentGet: " + netResult.toString());
	}

	@Test
	public void getFileContentGet2() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentGet(
				"http://sky.tvxio.com/v3_0/SKY2/tou0/api/item/730627/",
				getDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				"218.92.225.58:80");
		System.out.println("getFileContentGet: " + netResult.toString());
	}

	@Test
	public void getFileContentPostCycle() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> postDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentPostCycle(
				"http://www.baidu.com",
				getDataMap,
				postDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null,
				NetLIB.CYCLETYPE_FIXED_INTERVAL,
				3,
				3);
		System.out.println("getFileContentPostCycle: " + netResult.toString());
	}

	@Test
	public void getFileContentGetCycle() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContentGetCycle(
				"http://www.baidu.com",
				getDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null,
				NetLIB.CYCLETYPE_FIXED_INTERVAL,
				3,
				3);
		System.out.println("getFileContentGetCycle: " + netResult.toString());
	}

	@Test
	public void getFileContent() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> postDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContent(
				HttpMethod.GET,
				"http://www.baidu.com",
				getDataMap,
				postDataMap,
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null);
		System.out.println("getFileContent: " + netResult.toString());
	}

	@Test
	public void getFileContent2() throws Exception {
		Map<String, String> getDataMap = new HashMap<>();
		Map<String, String> headerMap = new HashMap<>();

		NetResult netResult = NetLIB.getFileContent2(
				HttpMethod.GET,
				"http://www.baidu.com",
				getDataMap,
				"",
				headerMap,
				Tool.ENCODING,
				3000,
				6000,
				true,
				200,
				null);
		System.out.println("getFileContent2: " + netResult.toString());
	}
}
