package com.example.takehome.huynh.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.takehome.huynh.handler.ContinentRequestHandler;
import com.example.takehome.huynh.model.rest.ContinentDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 * 
 *         Controller for querying of continent
 *
 */
@RestController
@Slf4j
public class ContinentRestController {

	/**
	 * @param codes
	 * @return list of continents by country codes
	 */
	@GetMapping("/continents")
	public ContinentDTO continent(@RequestParam("codes") String countryCodes) {
		try {
			String[] codes = countryCodes.split(",");

			ContinentDTO resp = new ContinentDTO();
			resp.setContinent(ContinentRequestHandler.getByCountryCodes(Arrays.asList(codes)));

			return resp;

		} catch (Exception e) {

			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return null;
	}

}