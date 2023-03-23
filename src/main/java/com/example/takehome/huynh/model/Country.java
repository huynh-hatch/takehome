package com.example.takehome.huynh.model;

import com.example.takehome.huynh.model.thirdparty.ContinentResponse;

/**
 * @author huynh
 *
 */
public class Country {

	private String code;
	private ContinentResponse continent;
	private String name;

	public Country() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ContinentResponse getContinent() {
		return continent;
	}

	public void setContinent(ContinentResponse continent) {
		this.continent = continent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
