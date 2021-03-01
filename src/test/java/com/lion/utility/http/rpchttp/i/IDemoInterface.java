package com.lion.utility.http.rpchttp.i;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.lion.utility.http.rpchttp.constant.Constant;
import com.lion.utility.http.rpchttp.entity.DynamicParam;
import com.lion.utility.http.rpchttp.entity.HttpConfig;
import com.lion.utility.http.rpchttp.entity.IResultTest;
import com.lion.utility.http.rpchttp.entity.RPCHttpResult;
import com.lion.utility.http.rpchttp.entity.RequestEntity;
import com.lion.utility.http.rpchttp.entity.InputAddUserInfo;
import com.lion.utility.http.rpchttp.entity.Item;
import com.lion.utility.http.rpchttp.entity.UserInfo;

@HttpConfig(url = "")
public interface IDemoInterface {
	@HttpConfig(url = "/getAppConfig1/{userid}/", method = Constant.REQUESTMETHOD_GET, targetEncoding = Constant.ENCODING, connectTimeoutSecond = 1, readTimeoutSecond = 3)
	List<String> getAppConfig1(
			@PathVariable("userid") int userid,
			@RequestParam("name") String name,
			@RequestHeader("Accept-Encoding") String encoding,
			@RequestHeader("Keep-Alive") long keepAlive) throws Exception;

	// RequestBody 只能有一个参数，所以不需要指定命名
	@HttpConfig(url = "/getAppConfig2/", method = Constant.REQUESTMETHOD_POST)
	List<String> getAppConfig2(@RequestBody Object test) throws Exception;

	@HttpConfig(url = "/getPolicyNextExecuteTime", method = Constant.REQUESTMETHOD_GET)
	IResultTest<String> getPolicyNextExecuteTime(
			DynamicParam dynamicParam,
			@RequestHeader("Accept-Encoding") String encoding,
			@RequestParam("policyId") int policyId) throws Exception;

	// ---------------------------------

	@HttpConfig(url = "/i/getUserInfo")
	RPCHttpResult<IResultTest<UserInfo>> getUserInfoNew(@RequestParam("userid") int userid) throws Exception;

	@HttpConfig(url = "/i/getUserInfo")
	IResultTest<UserInfo> getUserInfo(@RequestParam("userid") int userid) throws Exception;

	@HttpConfig(url = "/i/getUserInfoPath/{userid}/{type}/")
	IResultTest<UserInfo> getUserInfoPath(@PathVariable("userid") int userid, @PathVariable("type") int type) throws Exception;

	@HttpConfig(url = "/i/addUserInfo", method = Constant.REQUESTMETHOD_POST)
	IResultTest<Object> addUserInfo1(@RequestEntity InputAddUserInfo inputAddUserInfo) throws Exception;

	@HttpConfig(url = "/i/addUserInfo2", method = Constant.REQUESTMETHOD_POST)
	IResultTest<Object> addUserInfo2(@RequestBody InputAddUserInfo inputAddUserInfo) throws Exception;

	// -----------------------

	@HttpConfig(url = "/index.html")
	String index1(DynamicParam dynamicParam) throws Exception;

	@HttpConfig(url = "/index.html")
	void index2(DynamicParam dynamicParam) throws Exception;

	String index3(DynamicParam dynamicParam) throws Exception;

	// -----------------------

	@HttpConfig(url = "/v3_0/SKY2/tou0/api/item/{itemid}/")
	Item getItem(DynamicParam dynamicParam, @PathVariable("itemid") int itemid) throws Exception;

	// -----------------------
	@HttpConfig(url = "/test2.html")
	String test1() throws Exception;

	@HttpConfig(url = "/test.html")
	String test3() throws Exception;

	@HttpConfig(url = "/test.html")
	boolean test2() throws Exception;

}
