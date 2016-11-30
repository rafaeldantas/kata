package com.nokia.marsrovers;

import java.util.List;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class UIMock implements UI {

	private String upperRightCoordinates;

	private String roverInitialPosition;

	private String roverMovingInstructions;

	private Exception alert;

	private List<Rover> results;

	public UIMock withUpperRightCoordinates(String upperRightCoordinates) {
		this.upperRightCoordinates = upperRightCoordinates;
		return this;
	}

	public UIMock withRoverInitialPosition(String roverInitialPosition) {
		this.roverInitialPosition = roverInitialPosition;
		return this;
	}

	public UIMock withRoverMovingInstructions(String roverMovingInstructions) {
		this.roverMovingInstructions = roverMovingInstructions;
		return this;
	}

	@Override
	public String selectUpperRightCoordinates() {
		return upperRightCoordinates;
	}

	@Override
	public String selectRoverInitialPosition() {
		return roverInitialPosition;
	}

	@Override
	public String selectRoverMovingInstructions() {
		return roverMovingInstructions;
	}

	@Override
	public void showResults(List<Rover> results) {
		this.results = results;

	}

	@Override
	public void alert(Exception e) {
		alert = e;
	}

	public Exception getAlert() {
		return alert;
	}

	public List<Rover> getResults() {
		return results;
	}
}
