package com.nokia.marsrovers;

import java.util.List;

/**
 * @author rafaeldantas
 * 
 */
public interface UI {

	/**
	 * 
	 * @return the entered {@link String}
	 */
	String selectUpperRightCoordinates();

	/**
	 * 
	 * @return the entered {@link String}
	 */
	String selectRoverInitialPosition();

	/**
	 * 
	 * @return the entered {@link String}
	 */
	String selectRoverMovingInstructions();

	/**
	 * Displays the execution results
	 * 
	 * @param results
	 */
	void showResults(List<Rover> results);

	/**
	 * Displays error
	 * 
	 * @param e
	 */
	void alert(Exception e);

}
