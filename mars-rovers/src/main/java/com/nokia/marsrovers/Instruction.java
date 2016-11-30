package com.nokia.marsrovers;

import java.util.ArrayList;
import java.util.List;

/**
 * Instructions that are passed to the {@link Rover} Possible values are:<br>
 * 
 * <ol>
 * L (Left)
 * </ol>
 * <ol>
 * R (Right)
 * </ol>
 * <ol>
 * M (Move)
 * </ol>
 * 
 * @author rafaeldantas
 * 
 */
public enum Instruction {

	L {
		@Override
		public Rover execute(Rover rover) {
			return rover.turnLeft();
		}
	},
	R {
		@Override
		public Rover execute(Rover rover) {
			return rover.turnRight();
		}
	},
	M {
		@Override
		public Rover execute(Rover rover) {
			return rover.move();
		}
	};

	/**
	 * 
	 * @param instructions
	 * @return {@link List} of {@link Instruction}s to be executed by a
	 *         {@link Rover}
	 */
	public static List<Instruction> fromString(String instructions) {
		List<Instruction> instructionList = new ArrayList<Instruction>();
		for (char instruction : instructions.toCharArray()) {
			instructionList.add(fromName(instruction));
		}
		return instructionList;
	}

	/**
	 * 
	 * @param name
	 * @return {@link Instruction}
	 */
	static Instruction fromName(char name) {
		for (Instruction instruction : values()) {
			if (instruction.name().equals(String.valueOf(name))) {
				return instruction;
			}
		}
		throw new IllegalArgumentException("Could no find Instruction with name " + name);
	}

	/**
	 * 
	 * @param rover
	 * @return {@link Rover}
	 */
	public abstract Rover execute(Rover rover);
}
