package com.nokia.marsrovers;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class Position {

	private final int x;

	private final int y;

	private final Direction direction;

	public Position(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Position(String inputLine) {
		String[] stringValues = inputLine.trim().split(" ");
		if (stringValues.length < 2 || stringValues.length > 3) {
			throw new IllegalArgumentException("I can't parse this, 2 or 3 Parameters only!");
		}
		this.x = Integer.valueOf(stringValues[0]);
		this.y = Integer.valueOf(stringValues[1]);
		this.direction = stringValues.length == 3 ? Direction.fromString(stringValues[2]) : null;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Position left() {
		return new Position(x, y, direction.rotate(-90));
	}

	public Position right() {
		return new Position(x, y, direction.rotate(90));
	}

	public Position move() {
		return new Position(x + direction.xStep(), y + direction.yStep(), direction);
	}

	public void validate(Position gridSize) {
		if (x < 0 || y < 0 || x > gridSize.getX() || y > gridSize.getY()) {
			throw new IllegalArgumentException("Oops, the rover is out of the grid, at position " + this);
		}
	}

	@Override
	public String toString() {
		return x + " " + y + " " + direction;
	}
}
