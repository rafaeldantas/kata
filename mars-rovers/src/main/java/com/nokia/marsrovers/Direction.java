package com.nokia.marsrovers;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents all possible directions a {@link Rover} can go, using full
 * rotation degrees
 * 
 * @author rafaeldantas
 * 
 */
public enum Direction {

	// Might be a bit overkill, but gives room for refactoring, to implement
	// something like SW(225,0.5,0.5), for instance
	W(0, -1, 0), N(90, 0, 1), E(180, 1, 0), S(270, 0, -1);

	private static final Map<Integer, Direction> values = new HashMap<Integer, Direction>();

	static {
		for (Direction compass : values()) {
			values.put(compass.degrees, compass);
		}
	}

	/**
	 * The number in degrees that matches this {@link Direction}
	 */
	private final int degrees;

	/**
	 * The unit value that affects the X Axis of the Grid when the {@link Rover}
	 * moves in this {@link Direction}
	 */
	private final int xStep;

	/**
	 * The unit value that affects the Y Axis of the Grid when the {@link Rover}
	 * moves in this {@link Direction}
	 */
	private final int yStep;

	public int xStep() {
		return xStep;
	}

	public int yStep() {
		return yStep;
	}

	Direction(int degrees, int xStep, int yStep) {
		this.degrees = degrees;
		this.xStep = xStep;
		this.yStep = yStep;
	}

	/**
	 * 
	 * 
	 * @param degrees
	 * @return {@link Direction}
	 */
	public static Direction fromDegrees(int degrees) {
		if (degrees < 0) {
			return values.get(360 + degrees);
		}
		if (degrees >= 360) {
			return values.get(degrees - 360);
		}
		if (values.containsKey(degrees)) {
			return values.get(degrees);
		}

		throw new IllegalArgumentException("Could not find Direction with degree " + degrees);
	}

	/**
	 * 
	 * @param name
	 * @return {@link Direction}
	 */
	public static Direction fromString(String name) {
		for (Direction direction : values()) {
			if (direction.name().equals(name)) {
				return direction;
			}
		}
		throw new IllegalArgumentException("Could not find Direction with id " + name);
	}

	/**
	 * 
	 * @param rotation
	 * @return {@link Direction}
	 */
	public Direction rotate(int rotation) {
		return fromDegrees(degrees + rotation);
	}

}
