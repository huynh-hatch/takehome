package com.example.takehome.huynh.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.takehome.huynh.cache.impl.CountryCache;
import com.example.takehome.huynh.client.GraphqlClient;
import com.example.takehome.huynh.model.Country;

public class CountryService {

	private static final Logger log = LoggerFactory.getLogger(CountryService.class);

	/**
	 * Service to get Country from country code
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