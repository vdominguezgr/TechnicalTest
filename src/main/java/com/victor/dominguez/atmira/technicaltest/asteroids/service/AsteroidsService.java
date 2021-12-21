package com.victor.dominguez.atmira.technicaltest.asteroids.service;

import java.util.List;

import com.victor.dominguez.atmira.technicaltest.asteroids.dto.AsteroidsResponse;
import com.victor.dominguez.atmira.technicaltest.asteroids.exception.AsteroidsException;

public interface AsteroidsService {

	public List<AsteroidsResponse> calculateTop3RiskAsteroids(final Integer days) throws AsteroidsException;

}
