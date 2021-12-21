package com.victor.dominguez.atmira.technicaltest.asteroids.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.victor.dominguez.atmira.technicaltest.asteroids.dto.AsteroidsResponse;
import com.victor.dominguez.atmira.technicaltest.asteroids.exception.AsteroidsException;
import com.victor.dominguez.atmira.technicaltest.asteroids.exception.ValidationException;
import com.victor.dominguez.atmira.technicaltest.asteroids.service.AsteroidsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Validated
@RestController
public class AsteroidsController {

	private final AsteroidsService asteroidsService;

	@GetMapping("/asteroids")
	public ResponseEntity<List<AsteroidsResponse>> getTopThreeRiskAsteroids(
			@RequestParam(name = "days", required = false) final Object daysNumber)
			throws ValidationException, AsteroidsException {
		var validatedDaysNumber = this.validateInputParameter(daysNumber);
		return ResponseEntity.ok().body(asteroidsService.calculateTop3RiskAsteroids(validatedDaysNumber));
	}

	@ExceptionHandler(AsteroidsException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final String asteroidsExceptionHandler(final AsteroidsException e) {
		return '"' + e.getMessage() + '"';
	}

	@ExceptionHandler({ ValidationException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final String validationExceptionHandler(final ValidationException e) {
		return '"' + e.getMessage() + '"';
	}

	private Integer validateInputParameter(Object daysNumber) throws ValidationException {
		if (daysNumber != null) {
			try {
				var result = Integer.valueOf((String) daysNumber);
				if (result >= 1 && result <= 7) {
					return result;
				} else {
					throw new ValidationException("Input parameter 'days' need to meet the range between 1 and 7");
				}

			} catch (NumberFormatException ex) {
				throw new ValidationException("Input parameter 'days' is not a number");
			}
		} else {
			throw new ValidationException("Input parameter 'days' is required");
		}
	}

}
