package com.example.takehome.huynh.cache;

import java.util.Map;

/**
 * @author huynh
 *
 *         Abstract base cache
 *
 * @param <T>
 */
public abstract class BaseCache<T> implements ICache<T> {

	public abstract Map<String, T> getCacheInstance();

	public T getByCode(String code) {

		return getCacheInstance().get(code);
	}

	@Override
	public void put(String key, T object) {

		if (key != null && getCacheInstance() != null) {
			getCacheInstance().put(key, object);
		}
	}
}