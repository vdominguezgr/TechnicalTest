package com.victor.dominguez.atmira.technicaltest.asteroids.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class AsteroidsResponse {

	@JsonProperty(value = "nombre", index = 1)
	private String name;

	@JsonProperty(value = "diametro", index = 2)
	private BigDecimal diameter;

	@JsonProperty(value = "velocidad", index = 3)
	private BigDecimal speed;

	@JsonProperty(value = "fecha", index = 4)
	private String date;

	@JsonProperty(value = "planeta", index = 5)
	private String planet;

}
