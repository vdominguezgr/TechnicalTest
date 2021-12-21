package com.victor.dominguez.atmira.technicaltest.asteroids.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class HttpClient {

	private final RestTemplate restTemplate;

	public String requestApiGet(final String uri) throws RestClientException {

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
			return response.getBody();
		} else {
			throw new RestClientException(
					"Unexpected Status code from api '" + uri + "'. StatusCode: " + response.getStatusCodeValue());
		}

	}

}
