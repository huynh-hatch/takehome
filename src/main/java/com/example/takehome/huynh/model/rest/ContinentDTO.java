package com.example.takehome.huynh.model.rest;

import java.util.List;

import com.example.takehome.huynh.model.Continent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author huynh
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class ContinentDTO {

	private List<Continent> continent;

	public ContinentDTO() {

	}

	public List<Continent> getContinent() {
		return continent;
	}

	public void setContinent(List<Continent> continent) {
		this.continent = continent;
	}

}
