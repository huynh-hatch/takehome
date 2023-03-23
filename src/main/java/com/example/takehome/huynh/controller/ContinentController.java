package com.example.takehome.huynh.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.takehome.huynh.handler.ContinentRequestHandler;
import com.example.takehome.huynh.model.Continent;

@Controller
public class ContinentController {

	private static final Logger log = LoggerFactory.getLogger(ContinentController.class);

	@QueryMapping
	public List<Continent> continent(@Argument List<String> codes) {
		try {
			return ContinentRequestHandler.getByCountryCodes(codes);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

}