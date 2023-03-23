package com.example.takehome;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.example.takehome.huynh.model.Continent;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class ContinentControllerTests {

	@Autowired
	private GraphQlTester graphQlTester;

	@Test
	void getSameContinents() {

		String query = "query {continent(codes: [\"US\",\"CA\"]) {  countries   name   otherCountries  }}";

		List<Continent> continents = graphQlTester.document(query).execute().path("data.continent[*]").entityList(Continent.class).get();

		Assertions.assertTrue(continents.size() > 0);
		Assertions.assertTrue(continents.get(0).getName().equals("North America"));
	}
	
	@Test
	void getDiffContinents() {

		String query = "query {continent(codes: [\"US\",\"JP\"]) {  countries   name   otherCountries  }}";

		List<Continent> continents = graphQlTester.document(query).execute().path("data.continent[*]").entityList(Continent.class).get();

		Assertions.assertTrue(continents.size() == 2);
		Assertions.assertTrue(continents.get(0).getName().equals("North America") || continents.get(0).getName().equals("Asia"));
	}
}
