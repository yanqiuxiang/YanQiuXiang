package com.yqx.jurisdiction.util;

import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCacheClientEx
{
	private static MemCacheClientEx mMemCacheClientEx = new MemCacheClientEx();
	private MemCachedClient mMemCachedClient = null;

	private MemCacheClientEx()
	{
	}

	public static MemCacheClientEx getInstance()
	{
		return mMemCacheClientEx;
	}

	public boolean initClient(String host, int port, int initConnNums, int minConnNums, int maxConnNums)
	{
		String[] serverlist = {host + ":" + port};
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(serverlist);
		pool.setFailover(true);
		pool.setInitConn(initConnNums); // 设置初始连接  
		pool.setMinConn(minConnNums);// 设置最小连接  
		pool.setMaxConn(maxConnNums); // 设置最大连接  
		pool.setMaxIdle(1000 * 60 * 60 * 3); // 设置每个连接最大空闲时间3个小时  
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		mMemCachedClient = new MemCachedClient();
		return true;
	}

	public MemCachedClient getMemCachedClient()
	{
		return mMemCachedClient;
	}

	public boolean setData(String key, Object value, boolean serialize, int timeout)
	{
		if (serialize)
		{
			String jsonString = JSON.toJSONString(value);
			if (timeout > 0)
				return mMemCachedClient.set(key, jsonString, new Date(timeout * 1000));
			else
				return mMemCachedClient.set(key, jsonString);
		}
		else
		{
			if (timeout > 0)
				return mMemCachedClient.set(key, value, new Date(timeout * 1000));
			else
				return mMemCachedClient.set(key, value);
		}
	}

	public Object getData(String key, boolean serialize)
	{
		Object value = mMemCachedClient.get(key);
		if (value == null) return null;
		if (serialize)
			return JSON.parseObject((String) value);
		else
			return value;
	}
}
