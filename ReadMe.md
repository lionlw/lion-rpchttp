# RPCHTTP机制

## 特点
注解式http服务请求机制，避免了冗长的http接口请求操作，提供了类似于rpc的体验。

## 集成方式

### 客户端项目（即调用方）

#### 定义接口
```
@HttpConfig(url = "/i")
public interface IUserInfo {
	@HttpConfig(url = "/getUserInfo")
	IResult<UserInfo> getUserInfo(@RequestParam("userid") int userid) throws Exception;

	@HttpConfig(url = "/i/getUserInfo")
	RPCHttpResult<IResult<UserInfo>> getUserInfoNew(@RequestParam("userid") int userid) throws Exception;

	@HttpConfig(url = "/getUserInfoPath/{userid}/{type}/")
	IResult<UserInfo> getUserInfoPath(@PathVariable("userid") int userid, @PathVariable("type") int type) throws Exception;

	@HttpConfig(url = "/addUserInfo", method = Constant.REQUESTMETHOD_POST)
	IResult<Object> addUserInfo(@RequestEntity InputAddUserInfo inputAddUserInfo) throws Exception;

	@HttpConfig(url = "/addUserInfo2", method = Constant.REQUESTMETHOD_POST)
	IResult<Object> addUserInfo2(@RequestBody InputAddUserInfo inputAddUserInfo) throws Exception;

	@HttpConfig(url = "/search/error.html")
	String index() throws Exception;

	@HttpConfig(url = "/v3_0/SKY2/tou0/api/item/{itemid}/")
	Item getItem(DynamicParam dynamicParam, @PathVariable("itemid") int itemid) throws Exception;
}
```

#### 初始化RPCHttp客户端
建议放在加载配置中心代码处。
```
		RPCHttpClient rpcHttpClient = new RPCHttpClient(Tool.newArrayList(
				new RPCHttpServiceInfo("http://cmcservice.test.tvxio.com/", ICMC.class),
				new RPCHttpServiceInfo("http://127.0.0.1:8080/DemoInterface-boot", IDemoInterface-boot.class)));

		RPCHttpClientConfig rpcHttpClientConfig = new RPCHttpClientConfig();
		rpcHttpClient.start();

		rpchttpTest.icmc = rpcHttpClient.getInstance(ICMC.class);
		rpchttpTest.iDemoInterface-boot = rpcHttpClient.getInstance(IDemoInterface-boot.class);
```

注意：
rpcHttpClient.setRPCHttpClientConfig(RPCHttpClientConfig rpcHttpClientConfig);
可进行细致化配置。

#### 获取实例
单例调用，比如弄成静态变量
```
IUserInfo testI = rpcHttpClient.getInstance(IUserInfo.class);
```

#### 执行方法
```
IResult<UserInfo> iResult1 = testI.getUserInfo(1);
```

#### HttpConfig注解说明
可用于interface与method上。除url外，其余配置项都是method注解优先级高于interface注解。
配置项|说明|默认值
----|-----|------
url|地址|	
method|请求方式|Constant.REQUESTMETHOD_GET
targetEncoding|目标编码|UTF-8
connectTimeoutSecond|链接超时秒数|3
readTimeoutSecond|读取超时秒数|10

#### 方法参数类型说明
出于通用考虑，返回值可任意类型。
配置项|说明
----|-----
@RequestParam("name") String name|get/post参数，括号内的name为请求的key，后面的name为对应的value
@PathVariable("userid") int userid|路径参数，括号内的userid为路径中的变量名，后面的userid为对应的value
@RequestBody test1 test|以post json方式传递实体
@RequestEntity InputAddUserInfo inputAddUserInfo|将实体中的属性作为key，进行get/post发送，只支持一层属性
@RequestHeader("Accept-Encoding") String encoding|请求头部，括号内的为header的key，后面的encoding为对应的value
DynamicParam dynamicParam|动态参数，用于满足url等需在调用时动态变更的场景

#### 方法返回值说明
返回值分2种：
原始类型，与接口输出类型相对应，如：
```
	@HttpConfig(url = "/i/getUserInfo")
	IResult<UserInfo> getUserInfo(@RequestParam("userid") int userid) throws Exception;
```
封装类型，RPCHttpResult<原始类型>，其封装了网络调用的输入输出（NetResult），用于排错。如：
```
	@HttpConfig(url = "/i/getUserInfo")
	RPCHttpResult<IResult<UserInfo>> getUserInfoNew(@RequestParam("userid") int userid) throws Exception;
```

#### RPCHttpClientConfig说明
配置项|说明|默认值
----|-----|----
bindIpPort|绑定ip端口，为空则表示不使用（127.0.0.1:8080）|空
isDebug|是否debug模式|false
logLevel|日志输出级别|Constant.LOGLEVEL_ERROR 异常
isMock|是否mock server数据（用于当server无法连接时，模拟server数据返回）|false
