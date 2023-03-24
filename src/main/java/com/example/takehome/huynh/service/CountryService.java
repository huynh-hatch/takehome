package com.example.takehome.huynh.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.example.takehome.huynh.cache.impl.CountryCache;
import com.example.takehome.huynh.client.GraphqlClient;
import com.example.takehome.huynh.model.Country;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 * 
 *         A service to get the Country information by Country Code.
 *
 */
@Slf4j
public class CountryService {


	/**
	 * Service to get Country from country code.
	 * 
	 * First, check if the information is in the local cache. If not, invoke web service call.
	 * 
	 * @param code
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public Country getByCode(String code) throws URISyntaxException, IOException {

		log.debug("code={}", code);

		// Try to get from cache
		Country country = CountryCache.getInstance().getByCode(code);

		if (country == null) {

			// If cache doesnt exists then get it from web server
			country = GraphqlClient.getCountryByCode(code);

			if (country != null) {
				// Puts it in cache
				CountryCache.getInstance().put(country.getCode(), country);
			}
		}

		return country;
	}

}