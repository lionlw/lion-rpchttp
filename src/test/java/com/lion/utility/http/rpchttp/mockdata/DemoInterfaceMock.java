package com.lion.utility.http.rpchttp.mockdata;

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
import com.lion.utility.http.rpchttp.i.IDemoInterface;

public class DemoInterfaceMock implements IDemoInterface {

	@Override
	public List<String> getAppConfig1(int userid, String name, String encoding, long keepAlive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAppConfig2(Object test) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResultTest<String> getPolicyNextExecuteTime(DynamicParam dynamicParam, String encoding, int policyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RPCHttpResult<IResultTest<UserInfo>> getUserInfoNew(int userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResultTest<UserInfo> getUserInfo(int userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResultTest<UserInfo> getUserInfoPath(int userid, int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResultTest<Object> addUserInfo1(InputAddUserInfo inputAddUserInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResultTest<Object> addUserInfo2(InputAddUserInfo inputAddUserInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String index1(DynamicParam dynamicParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void index2(DynamicParam dynamicParam) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String index3(DynamicParam dynamicParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(DynamicParam dynamicParam, int itemid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String test1() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String test3() throws Exception {
		// TODO Auto-generated method stub
		return "mock test3";
	}

	@Override
	public boolean test2() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
