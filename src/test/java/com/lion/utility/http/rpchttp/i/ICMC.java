package com.lion.utility.http.rpchttp.i;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.lion.utility.http.rpchttp.constant.Constant;
import com.lion.utility.http.rpchttp.entity.DynamicParam;
import com.lion.utility.http.rpchttp.entity.HttpConfig;
import com.lion.utility.http.rpchttp.entity.IResultTest;
import com.lion.utility.http.rpchttp.entity.App;

@HttpConfig(url = "/i")
public interface ICMC {
	@HttpConfig(url = "/getApp", method = Constant.REQUESTMETHOD_POST)
	IResultTest<App> getApp(@RequestHeader("Accept-Encoding") String encoding, @RequestParam("appCode") String appCode) throws Exception;
}
