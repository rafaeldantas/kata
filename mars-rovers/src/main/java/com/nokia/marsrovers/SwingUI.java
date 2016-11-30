package com.nokia.marsrovers;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * Swing implementation of the User Interface
 * 
 * @author rafaeldantas
 * 
 */
public class SwingUI implements UI {

	@Override
	public String selectUpperRightCoordinates() {
		return JOptionPane.showInputDialog("Upper-right coordinates of the plateau:");
	}

	@Override
	public String selectRoverInitialPosition() {
		return JOptionPane.showInputDialog("Rover's initial position:");
	}

	@Override
	public String selectRoverMovingInstructions() {
		return JOptionPane.showInputDialog("Rover’s moving instructions:");
	}

	@Override
	public void showResults(List<Rover> results) {
		StringBuilder builder = new StringBuilder();
		for (Rover rover : results) {
			builder.append(rover.getPosition() + "\n");
		}
		JOptionPane.showMessageDialog(null, builder.toString());
	}

	@Override
	public void alert(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}

}
