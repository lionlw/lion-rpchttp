package com.lion.utility.http.rpchttp;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.http.rpchttp.client.RPCHttpClient;
import com.lion.utility.http.rpchttp.constant.Constant;
import com.lion.utility.http.rpchttp.entity.DynamicParam;
import com.lion.utility.http.rpchttp.entity.IResultTest;
import com.lion.utility.http.rpchttp.entity.RPCHttpClientConfig;
import com.lion.utility.http.rpchttp.entity.RPCHttpResult;
import com.lion.utility.http.rpchttp.entity.RPCHttpServiceInfo;
import com.lion.utility.http.rpchttp.entity.App;
import com.lion.utility.http.rpchttp.entity.InputAddUserInfo;
import com.lion.utility.http.rpchttp.entity.Item;
import com.lion.utility.http.rpchttp.entity.UserInfo;
import com.lion.utility.http.rpchttp.i.ICMC;
import com.lion.utility.http.rpchttp.i.IDemoInterface;
import com.lion.utility.http.rpchttp.mockdata.DemoInterfaceMock;

public class rpchttpTest {
	public static ICMC icmc;
	public static IDemoInterface iDemoInterface;

	@BeforeClass
	public static void setUp() throws Exception {
		RPCHttpClient rpcHttpClient = new RPCHttpClient(Tool.newArrayList(
				new RPCHttpServiceInfo("http://cmcservice.test.tvxio.com/", ICMC.class),
				new RPCHttpServiceInfo("http://127.0.0.1:8080/DemoInterface", IDemoInterface.class)));

		RPCHttpClientConfig rpcHttpClientConfig = new RPCHttpClientConfig();
		rpcHttpClientConfig.setIsDebug(true);
		rpcHttpClientConfig.setLogLevel(Constant.LOGLEVEL_INOUTERROR);
		rpcHttpClient.setRPCHttpClientConfig(rpcHttpClientConfig);

		rpcHttpClient.start();

		rpchttpTest.icmc = rpcHttpClient.getInstance(ICMC.class);
		rpchttpTest.iDemoInterface = rpcHttpClient.getInstance(IDemoInterface.class);
	}

	@Test
	public void testMock() throws Exception {
		Map<Class<?>, Class<?>> services = new HashMap<>();
		services.put(IDemoInterface.class, DemoInterfaceMock.class);

		RPCHttpClient rpcHttpClient = new RPCHttpClient(Tool.newArrayList(
				new RPCHttpServiceInfo("http://127.0.0.1:8080/DemoInterface", services)));

		RPCHttpClientConfig rpcHttpClientConfig = new RPCHttpClientConfig();
		rpcHttpClientConfig.setIsDebug(true);
		rpcHttpClientConfig.setLogLevel(Constant.LOGLEVEL_INOUTERROR);
		rpcHttpClientConfig.setIsMock(true);
		rpcHttpClient.setRPCHttpClientConfig(rpcHttpClientConfig);

		rpcHttpClient.start();

		IDemoInterface iDemoInterface2 = rpcHttpClient.getInstance(IDemoInterface.class);

		System.out.println("testMock: " + iDemoInterface2.test3());
	}

	@Test
	public void testcmc_getApp() throws Exception {
		IResultTest<App> iResult = rpchttpTest.icmc.getApp("gzip, deflate", "Leaf");

		System.out.println("testcmc_getApp: " + JsonLIB.toJson(iResult));
	}

	@Test
	public void testDemoInterface_getUserInfo() throws Exception {
		IResultTest<UserInfo> iResult = rpchttpTest.iDemoInterface.getUserInfo(6666);
		System.out.println("testDemoInterface_getUserInfo: " + JsonLIB.toJson(iResult));
	}

	@Test
	public void testDemoInterface_getUserInfoNew() throws Exception {
		RPCHttpResult<IResultTest<UserInfo>> rpcHttpResult = rpchttpTest.iDemoInterface.getUserInfoNew(6666);
		System.out.println("testDemoInterface_getUserInfoNew: " + JsonLIB.toJson(rpcHttpResult));
	}

	@Test
	public void testDemoInterface_index1() throws Exception {
		DynamicParam dynamicParam = new DynamicParam();
		dynamicParam.setServiceUrl("http://www.baidu.com");
		System.out.println("testDemoInterface_index1: " + rpchttpTest.iDemoInterface.index1(dynamicParam));
	}

	@Test
	public void testDemoInterface_index2() throws Exception {
		DynamicParam dynamicParam = new DynamicParam();
		dynamicParam.setServiceUrl("http://www.baidu.com");
		rpchttpTest.iDemoInterface.index2(dynamicParam);
		System.out.println("testDemoInterface_index2: ");
	}

	@Test
	public void testDemoInterface_index3() throws Exception {
		DynamicParam dynamicParam = new DynamicParam();
		dynamicParam.setUrl("http://www.baidu.com/index.html");
		System.out.println("testDemoInterface_index3: " + rpchttpTest.iDemoInterface.index3(dynamicParam));
	}

	@Test
	public void testDemoInterface_getItem() throws Exception {
		DynamicParam dynamicParam = new DynamicParam();
		dynamicParam.setServiceUrl("http://sky.tvxio.com/");
		Item item = rpchttpTest.iDemoInterface.getItem(dynamicParam, 6441557);
		System.out.println("testDemoInterface_getItem: " + JsonLIB.toJson(item));
	}

	@Test
	public void testDemoInterface_getUserInfoPath() throws Exception {
		IResultTest<UserInfo> iResult = rpchttpTest.iDemoInterface.getUserInfoPath(100, 2);
		System.out.println("testDemoInterface_getUserInfoPath: " + JsonLIB.toJson(iResult));
	}

	@Test
	public void testDemoInterface_addUserInfo1() throws Exception {
		InputAddUserInfo inputAddUserInfo = new InputAddUserInfo();
		inputAddUserInfo.setUsername("rpchttptest2");
		inputAddUserInfo.setUserpass("333222111");
		IResultTest<Object> iResult = rpchttpTest.iDemoInterface.addUserInfo1(inputAddUserInfo);
		System.out.println("testDemoInterface_addUserInfo1: " + JsonLIB.toJson(iResult));
	}

	@Test
	public void testDemoInterface_addUserInfo2() throws Exception {
		InputAddUserInfo inputAddUserInfo = new InputAddUserInfo();
		inputAddUserInfo.setUsername("rpchttptest1");
		inputAddUserInfo.setUserpass("111222333");
		IResultTest<Object> iResult = rpchttpTest.iDemoInterface.addUserInfo2(inputAddUserInfo);
		System.out.println("testDemoInterface_addUserInfo2: " + JsonLIB.toJson(iResult));
	}
}
