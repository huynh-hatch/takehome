package com.example.takehome.huynh.cache;

public interface ICache<T> {

	public T getByCode(String code);
	
	public void put(String key, T object);
}