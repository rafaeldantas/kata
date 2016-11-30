package com.nokia.marsrovers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class PositionTest {

	@Test
	public void createTest() {
		Position oneTwoNorth = new Position("1 2 N");
		assertEquals(1, oneTwoNorth.getX());
		assertEquals(2, oneTwoNorth.getY());
		assertEquals(Direction.N, oneTwoNorth.getDirection());

		Position fourThreeSouth = new Position("4 3 S");
		assertEquals(4, fourThreeSouth.getX());
		assertEquals(3, fourThreeSouth.getY());
		assertEquals(Direction.S, fourThreeSouth.getDirection());
	}

	@Test(expected = NumberFormatException.class)
	public void createInvalidTest() {
		new Position("S X W");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFewArgumentsTest() {
		new Position("1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createManyArgumentsTest() {
		new Position("1 2 W S");
	}

	@Test
	public void validateTest() {
		Position oneTwoSouth = new Position("1 2 S");
		oneTwoSouth.validate(new Position("2 2"));

		Position sevenFiveWest = new Position("7 5 W");
		sevenFiveWest.validate(new Position("10 10"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidLowerTest() {
		Position minusOneTwoSouth = new Position("-1 2 S");
		minusOneTwoSouth.validate(new Position("2 2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidHigherTest() {
		Position sevenTwoSouth = new Position("7 2 S");
		sevenTwoSouth.validate(new Position("5 2"));
	}

}
