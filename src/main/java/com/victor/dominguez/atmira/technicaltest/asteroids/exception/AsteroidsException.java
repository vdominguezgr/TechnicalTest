package com.victor.dominguez.atmira.technicaltest.asteroids.exception;

public class AsteroidsException extends Exception {

	private static final long serialVersionUID = 1L;

	public AsteroidsException(final String message) {
		super(message);
	}

	public AsteroidsException(final Throwable e) {
		super(e);
	}

}
