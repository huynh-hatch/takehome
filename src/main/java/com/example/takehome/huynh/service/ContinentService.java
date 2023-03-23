package com.example.takehome.huynh.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.takehome.huynh.cache.impl.ContinentCache;
import com.example.takehome.huynh.client.GraphqlClient;
import com.example.takehome.huynh.model.thirdparty.ContinentResponse;

/**
 * @author huynh
 *
 */
public class ContinentService {

	private static final Logger log = LoggerFactory.getLogger(ContinentService.class);

	/**
	 * Service to get Continent from Continent code
	 * 
	 * @param code
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public ContinentResponse getByCode(String code) throws URISyntaxException, IOException {

		log.debug("code={}", code);

		// Try to get from cache

		ContinentResponse continent = ContinentCache.getInstance().getByCode(code);

		if (continent == null) {

			// If cache doesnt exists then get it from web server
			continent = GraphqlClient.getContinentByCode(code);

			if (continent != null) {
				// Puts it in cache
				ContinentCache.getInstance().put(continent.getCode(), continent);
			}
		}

		return continent;
	}

}