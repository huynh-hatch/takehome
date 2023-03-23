package com.example.takehome.huynh.model;

import com.example.takehome.huynh.model.thirdparty.ContinentResponse;

/**
 * @author huynh
 *
 */
public class Data {

	private Country country;
	private ContinentResponse continent;

	public Data() {

	}

	public ContinentResponse getContinent() {
		return continent;
	}

	public void setContinent(ContinentResponse continent) {
		this.continent = continent;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
