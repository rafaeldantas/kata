package com.nokia.marsrovers;

/**
 * Represents a NASA robotic rover.
 * 
 * @author rafaeldantas
 * 
 */
public class Rover {

	private final Position position;

	public Rover(Position initialPosition) {
		this.position = initialPosition;
	}

	public Rover(int x, int y, Direction direction) {
		this(new Position(x, y, direction));
	}

	public Rover turnLeft() {
		return new Rover(position.left());
	}

	public Direction getDirection() {
		return position.getDirection();
	}

	public Position getPosition() {
		return position;
	}

	public Rover turnRight() {
		return new Rover(position.right());
	}

	public Rover move() {
		return new Rover(position.move());
	}
}
