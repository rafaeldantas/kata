package com.nokia.marsrovers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rafaeldantas
 * 
 */
public class MissionControllerTest {

	private UIMock uiMock;

	private MissionController controller;

	@Before
	public void before() {
		uiMock = new UIMock();
		controller = new MissionController(uiMock, 1);
	}

	@Test
	public void missionOneTest() {
		uiMock.withUpperRightCoordinates("5 5").withRoverInitialPosition("1 2 N").withRoverMovingInstructions("LMLMLMLMM");
		controller.start();
		Rover result = uiMock.getResults().get(0);
		Assert.assertEquals(1, result.getPosition().getX());
		Assert.assertEquals(3, result.getPosition().getY());
		Assert.assertEquals(Direction.N, result.getDirection());
	}

	@Test
	public void missionTwoTest() {
		uiMock.withUpperRightCoordinates("5 5").withRoverInitialPosition("3 3 E").withRoverMovingInstructions("MMRMMRMRRM");
		controller.start();
		Rover result = uiMock.getResults().get(0);
		Assert.assertEquals(5, result.getPosition().getX());
		Assert.assertEquals(1, result.getPosition().getY());
		Assert.assertEquals(Direction.E, result.getDirection());
	}

	@Test
	public void alertTest() {
		uiMock.withUpperRightCoordinates("5 5").withRoverInitialPosition("6 3 E").withRoverMovingInstructions("MMRMMRMRRM");
		controller.start();
		Assert.assertNull(uiMock.getResults());
		Assert.assertEquals("Oops, the rover is out of the grid, at position 7 3 E", uiMock.getAlert().getMessage());
	}

}
