package com.victor.dominguez.atmira.technicaltest.asteroids.service.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class Neo {

	private Links links;
	@SerializedName("neo_reference_id")
	private String neoReferenceid;
	private String name;
	@SerializedName("nasa_jpl_url")
	private String nasaJplUrl;
	@SerializedName("absolute_magnitude_h")
	private double absoluteMagnitudeH;
	@SerializedName("estimated_diameter")
	private EstimatedDiameter estimatedDiameter;
	@SerializedName("is_potentially_hazardous_asteroid")
	private boolean potentiallyHazardousAsteroid;
	@SerializedName("close_approach_data")
	private List<CloseApproach> closeApproachData;
	@SerializedName("orbital_data")
	private OrbitalData orbitalData;

	public BigDecimal diameterAverage() {
		var minDiameter = new BigDecimal(estimatedDiameter.getKilometers().getEstimatedDiameterMin());
		var maxDiameter = new BigDecimal(estimatedDiameter.getKilometers().getEstimatedDiameterMax());
		return minDiameter.add(maxDiameter).divide(BigDecimal.valueOf(2), 3, RoundingMode.HALF_UP);
	}

	@Getter
	public static final class MissDistance {

		private String astronomical;
		private String lunar;
		private String kilometers;
		private String miles;
	}

	@Getter
	public static final class CloseApproach {

		@SerializedName("close_approach_date")
		private String closeApproachDate;
		@SerializedName("epoch_date_close_approach")
		private long epochDateCloseApproach;
		@SerializedName("relative_velocity")
		private RelativeVelocity relativeVelocity;
		@SerializedName("miss_distance")
		private MissDistance missDistance;
		@SerializedName("orbiting_body")
		private String orbitingBody;
	}

	@Getter
	public static final class EstimatedDiameter {

		private EstimatedDiameterUnit kilometers;
		private EstimatedDiameterUnit meters;
		private EstimatedDiameterUnit miles;
		private EstimatedDiameterUnit feet;
	}

	@Getter
	public static final class EstimatedDiameterUnit {

		@SerializedName("estimated_diameter_min")
		private String estimatedDiameterMin;
		@SerializedName("estimated_diameter_max")
		private String estimatedDiameterMax;
	}

	@Getter
	public static final class OrbitalData {

		@SerializedName("orbit_id")
		private String orbitId;
		@SerializedName("orbit_determination_date")
		private String orbitDeterminationDate;
		@SerializedName("orbit_uncertainty")
		private String orbitUncertainty;
		@SerializedName("minimum_orbit_intersection")
		private String minimumOrbitIntersection;
		@SerializedName("jupiter_tisserand_invariant")
		private String jupiterTisserandInvariant;
		@SerializedName("epoch_osculation")
		private String epochOsculation;
		@SerializedName("eccentricity")
		private String eccentricity;
		@SerializedName("semi_major_axis")
		private String semiMajorAxis;
		private String inclination;
		@SerializedName("ascending_node_longitude")
		private String ascendingNodeLongitude;
		@SerializedName("orbital_period")
		private String orbitalPeriod;
		@SerializedName("perihelion_distance")
		private String perihelionDistance;
		@SerializedName("perihelion_argument")
		private String perihelionArgument;
		@SerializedName("aphelion_distance")
		private String aphelionDistance;
		@SerializedName("perihelion_time")
		private String perihelionTime;
		@SerializedName("mean_anomaly")
		private String meanAnomaly;
		@SerializedName("mean_motion")
		private String meanMotion;
		private String equinox;
	}

	@Getter
	public static final class RelativeVelocity {

		@SerializedName("kilometers_per_second")
		private String kilometersPerSecond;
		@SerializedName("kilometers_per_hour")
		private String kilometersPerHour;
		@SerializedName("miles_per_hour")
		private String milesPerHour;
	}
}
