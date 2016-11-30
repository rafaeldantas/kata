package com.nokia.marsrovers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class DirectionTest {

	@Test
	public void rotationTest() {
		assertEquals(Direction.E, Direction.N.rotate(90));
		assertEquals(Direction.S, Direction.N.rotate(180));
		assertEquals(Direction.W, Direction.N.rotate(270));
		assertEquals(Direction.N, Direction.N.rotate(360));
		assertEquals(Direction.N, Direction.N.rotate(0));

		assertEquals(Direction.W, Direction.S.rotate(90));
		assertEquals(Direction.N, Direction.S.rotate(180));
		assertEquals(Direction.E, Direction.S.rotate(270));
		assertEquals(Direction.S, Direction.S.rotate(360));
		assertEquals(Direction.S, Direction.S.rotate(0));

		assertEquals(Direction.N, Direction.W.rotate(90));
		assertEquals(Direction.E, Direction.W.rotate(180));
		assertEquals(Direction.S, Direction.W.rotate(270));
		assertEquals(Direction.W, Direction.W.rotate(360));
		assertEquals(Direction.W, Direction.W.rotate(0));

		assertEquals(Direction.S, Direction.E.rotate(90));
		assertEquals(Direction.W, Direction.E.rotate(180));
		assertEquals(Direction.N, Direction.E.rotate(270));
		assertEquals(Direction.E, Direction.E.rotate(360));
		assertEquals(Direction.E, Direction.E.rotate(0));

	}

	@Test
	public void fromStringTest() {
		assertEquals(Direction.N, Direction.fromString("N"));
		assertEquals(Direction.S, Direction.fromString("S"));
		assertEquals(Direction.E, Direction.fromString("E"));
		assertEquals(Direction.W, Direction.fromString("W"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFromStringTest() {
		Direction.fromString("X");
	}

	@Test
	public void fromDegreesTest() {
		assertEquals(Direction.N, Direction.fromDegrees(90));
		assertEquals(Direction.E, Direction.fromDegrees(180));
		assertEquals(Direction.S, Direction.fromDegrees(270));
		assertEquals(Direction.W, Direction.fromDegrees(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFromDegreesTest() {
		Direction.fromDegrees(33);
	}

}
