package com.example.takehome.huynh.ratelimiter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.takehome.huynh.ratelimiter.model.RateLimiterInfo;

/**
 * @author huynh
 *
 *         Very basic singleton rate limiter which checks the user's number of requests against the allowed TPS
 */
public class RateLimter {

	private static final Logger log = LoggerFactory.getLogger(RateLimter.class);

	private static Map<String, RateLimiterInfo> USER_TRACKER;
	private static long PREV_TIMESTAMP;

	private static volatile RateLimter instance = null;

	public RateLimter() {
		USER_TRACKER = new HashMap<>();
		PREV_TIMESTAMP = System.currentTimeMillis();
	}

	/**
	 * 
	 * Checks if the user's TPS is below the allowed TPS
	 * 
	 * @param user
	 * @param allowTps
	 * @return
	 */
	public boolean isBelowLimit(String user, int allowTps) {

		// If the difference between the previous execution time and current time is more than 1 sec, then clear the data store
		if (System.currentTimeMillis() - PREV_TIMESTAMP > 1000L) {
			USER_TRACKER.clear();
		}

		PREV_TIMESTAMP = System.currentTimeMillis();

		RateLimiterInfo db = USER_TRACKER.get(user);

		if (db == null) {
			// First time user so add to the tracker
			db = new RateLimiterInfo(user);
			USER_TRACKER.put(user, db);
		}

		// Get user's TPS for that second
		int tps = db.getTPS();

		log.debug("TPS=> {} - {}={}/{}", PREV_TIMESTAMP, user, tps, allowTps);

		return tps < allowTps;
	}

	public static RateLimter getInstance() {

		RateLimter result = instance;
		if (instance == null) {

			synchronized (RateLimter.class) {
				result = instance;
				if (result == null)
					instance = result = new RateLimter();
			}

		}
		return instance;
	}
}