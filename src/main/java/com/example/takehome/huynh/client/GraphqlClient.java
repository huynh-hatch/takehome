package com.example.takehome.huynh.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.takehome.huynh.model.Country;
import com.example.takehome.huynh.model.GraphqlResponse;
import com.example.takehome.huynh.model.thirdparty.ContinentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author huynh
 *
 */
public class GraphqlClient {

	private static final Logger log = LoggerFactory.getLogger(GraphqlClient.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final String CODE = "#CODE#";

	private static final String ROOT_URI = "https://countries.trevorblades.com/";
	private static final String COUNTRY_QUERY = "{\"query\":\"query {\\n  country (code: \\\"#CODE#\\\" ) {\\n    code\\n    continent {\\n      code\\n      name\\n    }\\n    name    \\n  }\\n}\"}";
	private static final String CONTINENT_QUERY = "{\"query\":\"query {\\n continent(code: \\\"#CODE#\\\") {\\n    code\\n    name    \\n  \\tcountries {\\n      code\\n  \\t}\\n  }\\n}\"}";

	/**
	 * Queries external graphql service to get country code
	 * 
	 * @param code
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static Country getCountryByCode(String code) throws URISyntaxException, IOException {

		String query = StringUtils.replace(COUNTRY_QUERY, CODE, code);
		log.debug("query={}", query);

		HttpResponse httpResponse = invokeService(ROOT_URI, query);
		String textResponse = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
		log.debug("textResponse={}", textResponse);

		// Converts json string to object
		GraphqlResponse parsedResponse = OBJECT_MAPPER.readValue(textResponse, GraphqlResponse.class);

		if (parsedResponse != null && parsedResponse.getData() != null) {
			return parsedResponse.getData().getCountry();
		}
		return null;
	}

	/**
	 * Queries external graphql service to get country code
	 * 
	 * @param code
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static ContinentResponse getContinentByCode(String code) throws URISyntaxException, IOException {
		String query = StringUtils.replace(CONTINENT_QUERY, CODE, code);
		log.debug("query={}", query);

		HttpResponse httpResponse = invokeService(ROOT_URI, query);
		String textResponse = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
		log.debug("textResponse={}", textResponse);

		// Converts json string to object
		GraphqlResponse parsedResponse = OBJECT_MAPPER.readValue(textResponse, GraphqlResponse.class);

		if (parsedResponse != null && parsedResponse.getData() != null) {
			return parsedResponse.getData().getContinent();
		}
		return null;
	}

	public static HttpResponse invokeService(String url, String query) throws URISyntaxException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(query));
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json;charset=utf-8");
		httpPost.setHeader("Origin", "https://countries.trevorblades.com");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
		return client.execute(httpPost);

	}

}