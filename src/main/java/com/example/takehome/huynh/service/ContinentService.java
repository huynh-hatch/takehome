package com.example.takehome.huynh.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.example.takehome.huynh.cache.impl.ContinentCache;
import com.example.takehome.huynh.client.GraphqlClient;
import com.example.takehome.huynh.model.thirdparty.ContinentResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 *
 *         A service to get the Continent information by Continent Code.
 * 
 */
@Slf4j
public class ContinentService {

	

	/**
	 * Method to get Continent from Continent code.
	 * 
	 * First, check if the information is in the local cache. If not, invoke web service call.
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

			// If cache doesn't exists then get it from the web server
			continent = GraphqlClient.getContinentByCode(code);

			if (continent != null) {
				// Puts it in cache if value exists
				ContinentCache.getInstance().put(continent.getCode(), continent);
			}
		}

		return continent;
	}

}