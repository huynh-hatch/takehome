package com.example.takehome.huynh.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.example.takehome.huynh.cache.BaseCache;
import com.example.takehome.huynh.cache.ICache;
import com.example.takehome.huynh.model.Country;

/**
 * @author huynh
 * 
 *         Simple country cache which uses singleton
 *
 */
public class CountryCache extends BaseCache<Country> implements ICache<Country> {

	private static Map<String, Country> CACHE = null;

	private CountryCache() {
		CACHE = new HashMap<>();
	}

	// Lazy loading and avoids synchronization issues
	private static class InstanceHolder {

		static CountryCache INSTANCE = new CountryCache();
	}

	public static CountryCache getInstance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public Map<String, Country> getCacheInstance() {

		return CACHE;
	}

}