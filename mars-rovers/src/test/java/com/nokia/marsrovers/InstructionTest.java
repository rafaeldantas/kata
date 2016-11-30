package com.nokia.marsrovers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class InstructionTest {

	@Test
	public void parseTest() {
		assertEquals(Instruction.L, Instruction.fromString("L").get(0));
		assertEquals(Instruction.R, Instruction.fromString("R").get(0));
		assertEquals(Instruction.M, Instruction.fromString("M").get(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseInvalidTest() {
		Instruction.fromString("X");
	}

	@Test
	public void parseLineTest() {
		List<Instruction> instrunctions = Instruction.fromString("LRMLLRMMRLLM");
		assertEquals(12, instrunctions.size());
	}

	@Test
	public void fromNameTest() {
		assertEquals(Instruction.L, Instruction.fromName('L'));
		assertEquals(Instruction.R, Instruction.fromName('R'));
		assertEquals(Instruction.M, Instruction.fromName('M'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseInvalidNameTest() {
		Instruction.fromName('Y');
	}
}
