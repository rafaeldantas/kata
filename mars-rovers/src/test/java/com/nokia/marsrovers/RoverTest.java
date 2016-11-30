package com.nokia.marsrovers;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class RoverTest {

	@Test
	public void moveLeftTest() {
		Rover north = new Rover(1, 2, Direction.N);
		Assert.assertEquals(Direction.W, north.turnLeft().getDirection());

		Rover east = new Rover(1, 2, Direction.E);
		Assert.assertEquals(Direction.N, east.turnLeft().getDirection());

		Rover south = new Rover(1, 2, Direction.S);
		Assert.assertEquals(Direction.E, south.turnLeft().getDirection());

		Rover west = new Rover(1, 2, Direction.W);
		Assert.assertEquals(Direction.S, west.turnLeft().getDirection());
	}

	@Test
	public void moveRightTest() {
		Rover north = new Rover(1, 2, Direction.N);
		Assert.assertEquals(Direction.E, north.turnRight().getDirection());

		Rover east = new Rover(1, 2, Direction.E);
		Assert.assertEquals(Direction.S, east.turnRight().getDirection());

		Rover south = new Rover(1, 2, Direction.S);
		Assert.assertEquals(Direction.W, south.turnRight().getDirection());

		Rover west = new Rover(1, 2, Direction.W);
		Assert.assertEquals(Direction.N, west.turnRight().getDirection());
	}

	@Test
	public void rotationTest() {
		Direction orientation = new Rover(0, 0, Direction.N).turnRight().turnRight().turnLeft().turnRight().turnLeft().turnLeft()
				.getDirection();
		Assert.assertEquals(Direction.N, orientation);

		orientation = new Rover(0, 0, Direction.E).turnRight().turnLeft().turnRight().turnRight().getDirection();
		Assert.assertEquals(Direction.W, orientation);
	}

	@Test
	public void moveTest() {
		Position finalPosition = new Rover(1, 2, Direction.N).turnLeft().move().turnLeft().move().turnLeft().move().turnLeft().move()
				.move().getPosition();
		Assert.assertEquals(1, finalPosition.getX());
		Assert.assertEquals(3, finalPosition.getY());
		Assert.assertEquals(Direction.N, finalPosition.getDirection());

		finalPosition = new Rover(3, 3, Direction.E).move().move().turnRight().move().move().turnRight().move().turnRight().turnRight()
				.move().getPosition();
		Assert.assertEquals(5, finalPosition.getX());
		Assert.assertEquals(1, finalPosition.getY());
		Assert.assertEquals(Direction.E, finalPosition.getDirection());
	}
}
