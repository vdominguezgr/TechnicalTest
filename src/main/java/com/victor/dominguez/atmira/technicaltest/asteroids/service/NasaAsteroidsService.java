package com.victor.dominguez.atmira.technicaltest.asteroids.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.victor.dominguez.atmira.technicaltest.asteroids.dto.AsteroidsResponse;
import com.victor.dominguez.atmira.technicaltest.asteroids.exception.AsteroidsException;
import com.victor.dominguez.atmira.technicaltest.asteroids.http.HttpClient;
import com.victor.dominguez.atmira.technicaltest.asteroids.service.model.Neo;
import com.victor.dominguez.atmira.technicaltest.asteroids.service.model.NeoFeed;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class NasaAsteroidsService implements AsteroidsService {

	@NonNull
	private final HttpClient httpClient;

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final String API_URI = "https://api.nasa.gov/neo/rest/v1/feed?start_date=%1$s&end_date=%2$s&api_key=zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

	@Override
	public List<AsteroidsResponse> calculateTop3RiskAsteroids(final Integer days) throws AsteroidsException {

		try {
			LocalDate now = LocalDate.now();

			var response = new LinkedList<AsteroidsResponse>();
			this.getAsteroidsFromNasaApi(now.format(DATE_FORMATTER), now.plusDays(days).format(DATE_FORMATTER))
					.getNeos().entrySet()
					.forEach(entry -> response.addAll(this.neosAsteroidsResponseParser(entry.getKey(), entry.getValue()
							.stream().filter(Neo::isPotentiallyHazardousAsteroid).collect(Collectors.toList()))));

			return response.stream()
					.sorted(Comparator.comparing(AsteroidsResponse::getDiameter, Comparator.reverseOrder())).limit(3)
					.collect(Collectors.toList());

		} catch (RestClientException e) {
			log.error("[NasaAsteroidsService] Error requesting nasa api", e);
			throw new AsteroidsException("Error requesting nasa api: " + e.getMessage());
		} catch (JsonSyntaxException e) {
			log.error("[NasaAsteroidsService] Unexpected object type received from nasa api", e);
			throw new AsteroidsException("Unexpected object type received from nasa api: " + e.getMessage());
		} catch (Exception e) {
			log.error("[NasaAsteroidsService] Error calculating top 3 asteroids", e);
			throw new AsteroidsException("Error calculating top 3 asteroids: " + e.getMessage());
		}

	}

	private NeoFeed getAsteroidsFromNasaApi(final String startDate, final String endDate)
			throws RestClientException, JsonSyntaxException {
		return new Gson().fromJson(httpClient.requestApiGet(String.format(API_URI, startDate, endDate)), NeoFeed.class);
	}

	private List<AsteroidsResponse> neosAsteroidsResponseParser(final String date, final List<Neo> neos) {
		var result = new LinkedList<AsteroidsResponse>();
		neos.stream().forEach(neo -> {
			var speed = new BigDecimal(neo.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour());
			var planet = neo.getCloseApproachData().get(0).getOrbitingBody();
			result.add(AsteroidsResponse.builder().name(neo.getName()).diameter(neo.diameterAverage()).speed(speed)
					.date(date).planet(planet).build());
		});
		return result;
	}

}
