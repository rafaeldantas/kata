package aws.fleet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;
import aws.fleet.domain.SlotData;

public class HostsTest {

	@Test
	public void hostTest() {
		Host host = new Host(1, InstanceType.M1, new SlotData(10, 7, 3));
		assertEquals(1, host.id());
		assertEquals(InstanceType.M1, host.instanceType());
		assertEquals(10, host.slots().total());
		assertEquals(7, host.slots().busy());
		assertEquals(3, host.slots().free());
	}

	@Test(expected = IllegalArgumentException.class)
	public void noTypeTest() {
		new Host(1, null, new SlotData(10, 7, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void noSlotsTest() {
		new Host(1, InstanceType.M1, null);
	}

}
