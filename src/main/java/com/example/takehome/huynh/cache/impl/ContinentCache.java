package com.example.takehome.huynh.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.example.takehome.huynh.cache.BaseCache;
import com.example.takehome.huynh.cache.ICache;
import com.example.takehome.huynh.model.thirdparty.ContinentResponse;

/**
 * @author huynh
 * 
 *         Simple continent cache which uses singleton
 *
 */
public class ContinentCache extends BaseCache<ContinentResponse> implements ICache<ContinentResponse> {

	private static Map<String, ContinentResponse> CACHE = null;
	private static volatile ContinentCache instance = null;

	private ContinentCache() {
		CACHE = new HashMap<>();
	}

	/**
	 * Get instance of cache
	 * 
	 * @return
	 */
	public static ContinentCache getInstance() {

		ContinentCache result = instance;
		if (instance == null) {

			// Need to sychronize for thread-safe
			synchronized (ContinentCache.class) {
				result = instance;

				// Need to do double null check for synchronization
				if (result == null)
					instance = result = new ContinentCache();
			}

		}
		return instance;
	}

	@Override
	public Map<String, ContinentResponse> getCacheInstance() {

		return CACHE;
	}

}