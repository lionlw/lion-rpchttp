package com.lion.utility.http.rpchttp.client;

import javassist.ClassClassPath;
import javassist.ClassPool;
import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.JsonLIB;
import com.lion.utility.tool.log.LogLIB;
import com.lion.utility.http.rpchttp.entity.RPCHttpClientConfig;
import com.lion.utility.http.rpchttp.entity.RPCHttpServiceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * RPC http客户端
 * 
 * RPCHttpClient可以注册多个RPCHttpService，RPCService可以连接多个RPCConnect
 * 
 * @author lion
 *
 */
public class RPCHttpClient {
	// [start] 变量定义

	/**
	 * rpc http client配置
	 */
	protected RPCHttpClientConfig rpcHttpClientConfig;

	/**
	 * 注册服务清单
	 */
	protected List<RPCHttpServiceInfo> rpcHttpServiceInfos;

	/**
	 * RPC http客户端注册服务类数组（rpcHttpServiceInfos与rpcServices一一对应）
	 */
	protected List<RPCHttpService> rpcHttpServices = new ArrayList<>();
	/**
	 * mock数据实例缓存（key：类名，value：mock实例对象）
	 */
	protected Map<String, Object> mockDataMap = new HashMap<>();
	/**
	 * 代理缓存（key：类名，value：代理对象）
	 */
	protected Map<String, Object> proxyMap = new HashMap<>();

	/**
	 * 字节码类容器，必须全局唯一，否则无法实现在字节码中调用字节码生成的类
	 */
	protected ClassPool classPool;

	// [end]

	/**
	 * 初始化
	 * 
	 * @param rpcHttpServiceInfos
	 *            注册的服务
	 */
	public RPCHttpClient(List<RPCHttpServiceInfo> rpcHttpServiceInfos) {
		this.rpcHttpServiceInfos = rpcHttpServiceInfos;

		this.classPool = new ClassPool(true);
		// 解决tomcat classloader问题
		this.classPool.insertClassPath(new ClassClassPath(this.getClass()));
		this.rpcHttpClientConfig = new RPCHttpClientConfig();
	}

	/**
	 * 设置rpc客户端配置（需要在调用start前设置）
	 * 
	 * @param rpcHttpClientConfig
	 *            rpc http客户端配置
	 */
	public void setRPCHttpClientConfig(RPCHttpClientConfig rpcHttpClientConfig) {
		this.rpcHttpClientConfig = rpcHttpClientConfig;
	}

	/**
	 * 启动
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void start() throws Exception {
		// 打印配置信息
		LogLIB.info("rpcHttpClientConfig: " + JsonLIB.toJson(this.rpcHttpClientConfig));

		// mock数据处理
		if (this.rpcHttpClientConfig.getIsMock()) {
			this.loadMockData();
			LogLIB.info("isMockServerData, so don't connect server, return local mock server data");
			return;
		}

		if (this.rpcHttpClientConfig.getIsDebug()) {
			LogLIB.info("tmpdir: " + Tool.getTmpDir());
		}

		//加载注册服务
		this.loadRegisterServices();
	}

	/**
	 * 获取代理类实例
	 * 
	 * @param inferfaceClass
	 *            接口类
	 * @param <T>
	 *            泛型
	 * @return 结果
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> inferfaceClass) {
		if (this.rpcHttpClientConfig.getIsMock()) {
			// mock 数据
			return (T) this.mockDataMap.get(inferfaceClass.getName());
		}

		return (T) this.proxyMap.get(inferfaceClass.getName());
	}

	/**
	 * 加载注册服务
	 * 
	 * @throws Exception
	 *             异常
	 */
	private void loadRegisterServices() throws Exception {
		for (RPCHttpServiceInfo serviceInfo : this.rpcHttpServiceInfos) {
			RPCHttpService rpcHttpService = new RPCHttpService(this, serviceInfo);
			this.rpcHttpServices.add(rpcHttpService);

			//加载类代理缓存
			for (Class<?> inferfaceClass : serviceInfo.getRegisterServices().keySet()) {
				Object obj = BytecodeProxy.newProxyInstance(
						rpcHttpService,
						inferfaceClass);
				this.proxyMap.put(inferfaceClass.getName(), obj);
				LogLIB.info(serviceInfo.getServiceUrl() + ", rpcHttp proxyMap: " + JsonLIB.toJson(this.proxyMap.keySet()));
			}
		}
	}

	/**
	 * 加载mock数据（实例化）
	 * 
	 * @throws Exception
	 *             异常
	 */
	private void loadMockData() throws Exception {
		// 遍历所有注册服务 
		for (RPCHttpServiceInfo serviceInfo : this.rpcHttpServiceInfos) {
			for (Entry<Class<?>, Class<?>> entry : serviceInfo.getRegisterServices().entrySet()) {
				if (entry.getValue() != null) {
					this.mockDataMap.put(entry.getKey().getName(), entry.getValue().newInstance());
				}
			}
		}
	}
}