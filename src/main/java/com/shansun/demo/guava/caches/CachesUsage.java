package com.shansun.demo.guava.caches;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

/**
 * CacheBuilder usage
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-6-28
 */
public class CachesUsage {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		commonUsage();

		useCacheEviction();

		useRefresh();
	}

	static void commonUsage() throws InterruptedException, ExecutionException {
		LoadingCache<String, Object> cache = CacheBuilder.newBuilder() //
				.expireAfterAccess(3, TimeUnit.SECONDS) //
				.maximumSize(10) //
				.weakKeys() //
				.build(new CacheLoader<String, Object>() {

					@Override
					public Object load(String key) throws Exception {
						return "new-value-loaded-" + key;
					}

				});

		cache.put("init", 12345);

		System.out.println(cache.getIfPresent("init"));

		// ���cache�в����ڣ����Զ�����load����
		// ֻ��get/getAll��Ч��getIfPresent��Ч
		System.out.println(cache.get("123"));

		Thread.sleep(4000);

		System.out.println(cache.getIfPresent("init"));

		Thread.sleep(1000);

		// ���������ǣ�����������У�ֱ�ӷ��أ�
		// ���������û�У����ȵ���Callable.call���ɻ���ֵ��Ȼ�����õ�cache�У�������ֵ
		System.out.println(cache.get("init", new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return "new value";
			}
		}));
	}

	static void useCacheEviction() {
		// ����cache��С�Ļ��������Ƴ�
		Cache<String, Object> cache = CacheBuilder.newBuilder() //
				.maximumWeight(10)
				// ��������Ի�����Ȩ�ؼ���
				.weigher(new Weigher<String, Object>() {

					@Override
					public int weigh(String key, Object value) {
						// �ַ���Խ����Ȩ��Խ��
						return key.length();
					}
				})
				// ��������Ƴ�������
				.removalListener(new RemovalListener<String, Object>() {

					@Override
					public void onRemoval(RemovalNotification<String, Object> notification) {
						System.out.println(notification.getKey() + " has been removed!");
					}
				}).build(new CacheLoader<String, Object>() {

					@Override
					public Object load(String key) throws Exception {
						System.out.println("Loaded key=" + key);
						return "new-value-" + key;
					}
				});

		for (int i = 1; i <= 5; i++) {
			cache.put(Strings.repeat("A", i), i);
		}

		System.out.println("-----------------------------------------------");

		// ����ʱ��Ļ������ݲ���
		// expireAfterAccess(long, TimeUnit) & expireAfterWrite(long, TimeUnit)

		// �������õĻ������ݲ���
		// weakKeys & weakValues & softValues

		// �����Ƴ�����
		// Cache.invalidate & Cache.invalidateAll
	}

	static void useRefresh() throws InterruptedException, ExecutionException {
		LoadingCache<String, Object> cache = CacheBuilder.newBuilder() //
				.maximumSize(20) //
				.refreshAfterWrite(1, TimeUnit.SECONDS) //
				.removalListener(new RemovalListener<String, Object>() {

					@Override
					public void onRemoval(RemovalNotification<String, Object> notification) {
						System.out.println("Removing " + notification.getKey());
					}
				})
				// ��ͳ�ƹ���
				.recordStats().build(new CacheLoader<String, Object>() {

					@Override
					public Object load(String key) throws Exception {
						System.out.println("Loaded " + key);
						return key + "-" + System.currentTimeMillis();
					}

					@Override
					public ListenableFuture<Object> reload(final String key, Object oldValue) throws Exception {
						System.out.println("Reloaded " + key);

						if (key.equals("xujun")) {
							System.out.println("Immediate future [" + key + "]");
							return Futures.immediateFuture(oldValue);
						} else {
							ListenableFutureTask<Object> task = ListenableFutureTask.create(new Callable<Object>() {

								@Override
								public Object call() throws Exception {
									System.out.println("Called " + key);

									return key + "-RELOAD-" + System.currentTimeMillis();
								}
							});

							Executors.newSingleThreadExecutor().execute(task);

							return task;
						}
					}
				});

		cache.put("lanbo", 1234);

		cache.put("xujun", 2345);

		System.out.println(cache.get("xujun"));

		// ���»���
		// cache.refresh("lanbo");

		// ���ͳ������
		CacheStats stats = cache.stats();

		System.out.println(stats);

		Thread.sleep(3000);

	}

}
