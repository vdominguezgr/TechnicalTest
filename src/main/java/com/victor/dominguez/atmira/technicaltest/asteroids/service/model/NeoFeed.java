package com.victor.dominguez.atmira.technicaltest.asteroids.service.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class NeoFeed {

	private Links links;
	@SerializedName("element_count")
	private int elementCount;
	@SerializedName("near_earth_objects")
	private Map<String, List<Neo>> neos;

}
