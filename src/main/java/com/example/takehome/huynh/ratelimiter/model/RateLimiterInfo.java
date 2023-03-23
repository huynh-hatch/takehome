package com.example.takehome.huynh.ratelimiter.model;

import java.time.Instant;

public class RateLimiterInfo {

	private String user;
	private long timeStamp;
	private int numOfTxns;

	public RateLimiterInfo() {
		this.timeStamp = Instant.now().getEpochSecond();
		this.numOfTxns = 0;
	}

	public RateLimiterInfo(String user) {
		this();
		this.user = user;
	}

	public int getTPS() {
		++numOfTxns;
		
//		long currentTime = Instant.now().getEpochSecond();
//		double deltaTime = (currentTime - timeStamp);
//
//		int tps = (int) (numOfTxns / deltaTime);
//		
//		return tps;
		return numOfTxns;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNumOfTxns() {
		return numOfTxns;
	}

	public void setNumOfTxns(int numOfTxns) {
		this.numOfTxns = numOfTxns;
	}

}
