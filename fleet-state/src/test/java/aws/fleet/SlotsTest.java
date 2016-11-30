package aws.fleet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aws.fleet.domain.SlotData;

public class SlotsTest {

	@Test
	public void slotTest() {
		SlotData slots = new SlotData(10, 7, 3);
		assertEquals(10, slots.total());
		assertEquals(7, slots.busy());
		assertEquals(3, slots.free());
	}

	@Test(expected = IllegalArgumentException.class)
	public void moreBusyThenTotalTest() {
		new SlotData(10, 11, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void moreFreeThenTotalTest() {
		new SlotData(10, 11, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void moreBusyAndFreeThenTotalTest() {
		new SlotData(10, 5, 6);
	}
}
