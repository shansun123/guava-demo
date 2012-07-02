package com.shansun.demo.guava.caches;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * CacheBuilder usage
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-6-28
 */
public class CacheBuilderUsage {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		commonUsage();
	}

	private static void commonUsage() throws InterruptedException, ExecutionException {
		Cache<String, Object> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).maximumSize(10).weakKeys().build();

		cache.put("init", 12345);

		System.out.println(cache.getIfPresent("init"));

		Thread.sleep(4000);

		System.out.println(cache.getIfPresent("init"));

		Thread.sleep(1000);

		System.out.println(cache.get("init", new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return "new value";
			}
		}));
	}

}
