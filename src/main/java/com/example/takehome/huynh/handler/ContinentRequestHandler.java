package com.example.takehome.huynh.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.takehome.huynh.model.Continent;
import com.example.takehome.huynh.model.Country;
import com.example.takehome.huynh.model.thirdparty.ContinentResponse;
import com.example.takehome.huynh.service.ContinentService;
import com.example.takehome.huynh.service.CountryService;
import com.example.takehome.huynh.utils.AppUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 * 
 *         Handles the request for querying of continents by country code
 *
 */
@Slf4j
public class ContinentRequestHandler {

	/**
	 * Gets Continentes by country codes
	 * 
	 * @param countryCodes
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * 
	 */
	public static List<Continent> getByCountryCodes(List<String> countryCodes) throws URISyntaxException, IOException {

		log.debug("requested country codes={}", countryCodes);

		if (AppUtils.isEmpty(countryCodes)) {
			return null;
		}

		// Keeps a copy of already processed continents
		Map<String, Continent> continentsMap = new HashMap<>();

		CountryService countryService = new CountryService();
		ContinentService continentService = new ContinentService();

		for (String countryCode : countryCodes) {

			// Gets country code
			Country country = countryService.getByCode(countryCode);

			if (country != null) {

				// Gets continent by continent code from country above
				Continent continent = continentsMap.get(country.getContinent().getCode());

				if (continent == null) {

					// If local storage doesn't exists, then get from service
					ContinentResponse continentQuery = continentService.getByCode(country.getContinent().getCode());

					if (continentQuery != null) {

						continent = new Continent();
						// populate continent with web data
						continent.setName(continentQuery.getName());
						continent.setOtherCountries(continentQuery.getCountries().stream().map(Country::getCode)
								.collect(Collectors.toList()));

						// puts into local map
						continentsMap.put(country.getContinent().getCode(), continent);
					}

				}

				continent.getCountries().add(countryCode);
				// Filters country;
				continent.setOtherCountries(continent.getOtherCountries().stream().filter(c -> !c.equals(countryCode))
						.collect(Collectors.toList()));
			}

		}

		return new ArrayList<Continent>(continentsMap.values());
	}

}