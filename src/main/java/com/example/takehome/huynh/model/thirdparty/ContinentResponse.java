package com.example.takehome.huynh.model.thirdparty;

import java.util.List;

import com.example.takehome.huynh.model.Country;

/**
 * @author huynh
 *
 * Third party model
 */
public class ContinentResponse {

	private List<Country> countries;
	private String code;
	private String name;

	public ContinentResponse() {

	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
