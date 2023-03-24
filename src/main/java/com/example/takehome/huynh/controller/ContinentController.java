package com.example.takehome.huynh.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.takehome.huynh.handler.ContinentRequestHandler;
import com.example.takehome.huynh.model.Continent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 * 
 *         Controller for querying of continent
 *
 */
@Controller
@Slf4j
public class ContinentController {

	/**
	 * @param codes
	 * @return list of continents by country codes
	 */
	@QueryMapping
	public List<Continent> continent(@Argument List<String> codes) {
		try {

			return ContinentRequestHandler.getByCountryCodes(codes);

		} catch (Exception e) {

			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return null;
	}

}