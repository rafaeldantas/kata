package com.nokia.marsrovers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rafaeldantas
 * 
 */
public final class MissionController {

	private final int roversQtd;
	private final UI ui;

	private MissionController() {
		this.ui = new SwingUI();
		this.roversQtd = 2;
	}

	public MissionController(UI ui, int roversQtd) {
		this.ui = ui;
		this.roversQtd = roversQtd;
	}

	private void startMission() {
		List<Rover> results = new ArrayList<Rover>();
		String gridLine = ui.selectUpperRightCoordinates();
		for (int i = 0; i < roversQtd; i++) {
			Position gridSize = new Position(gridLine);
			Rover rover = startRover(gridSize);
			results.add(rover);
		}
		ui.showResults(results);

	}

	Rover startRover(Position gridSize) {
		String positionLine = ui.selectRoverInitialPosition();
		String instructions = ui.selectRoverMovingInstructions();
		Rover rover = new Rover(new Position(positionLine));

		List<Instruction> instructionList = Instruction.fromString(instructions);
		for (Instruction instruction : instructionList) {
			rover = instruction.execute(rover);
			rover.getPosition().validate(gridSize);
		}
		return rover;
	}

	final void start() {
		try {
			startMission();
		} catch (Exception e) {
			e.printStackTrace();
			ui.alert(e);
		}
	}

	public static void main(String[] args) {
		new MissionController().start();
	}

}
