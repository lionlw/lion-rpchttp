package com.lion.utility.http.httpmonitor;

import com.lion.utility.tool.common.Tool;
import com.lion.utility.http.httpmonitor.server.HttpServerMonitor;

public class HttpServerMonitorTest {

	public static void main(String[] args) throws Exception {

		HttpServerMonitor.init(
				"testHttpServerMonitor",
				"testHttpServerMonitor",
				"http://test.admin.tvxio.com:8080/RPCManage/",
				null,
				Tool.newHashSet("test.other.testapp"),
				null,
				null,
				true);

	}

}
