package com.example.takehome.huynh.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huynh
 *
 */
public class Continent {

	private List<String> countries;
	private String code;
	private String name;
	private List<String> otherCountries;

	public Continent() {

	}

	public List<String> getCountries() {
		
		if (countries == null) {
			countries = new ArrayList<>();
		}
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getOtherCountries() {
		if (otherCountries == null) {
			otherCountries = new ArrayList<>();
		}
		
		return otherCountries;
	}

	public void setOtherCountries(List<String> otherCountries) {
		this.otherCountries = otherCountries;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
