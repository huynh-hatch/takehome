package com.example.takehome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.takehome.huynh.controller.ContinentRestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(ContinentRestController.class)
public class ContinentRestControllerTests {
	@Autowired
	protected MockMvc mvc;

	@Autowired
	public ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void getSameContinents() throws Exception {

		String uri = "/continents?codes=US,CA";

		mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.continent").exists()).andExpect(MockMvcResultMatchers.jsonPath("$.continent[*].countries").isNotEmpty());
	}

	@Test
	void getDiffContinents() throws Exception {

		String uri = "/continents?codes=US,JP";

		mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.continent").exists()).andExpect(MockMvcResultMatchers.jsonPath("$.continent.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.continent[*].countries").isNotEmpty());
	}
}
