package aws.fleet;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;
import aws.fleet.domain.SlotData;
import aws.fleet.statistics.AggregatedStatistics;
import aws.fleet.statistics.Statistics;

public class StatisticsTest {

	private Set<Host> store;

	@Before
	public void setup() {
		store = new HashSet<>();
	}

	@Test
	public void hostParseTest() {
		// Empty
		store.add(new Host(1, InstanceType.M1, new SlotData(4, 0, 4)));
		store.add(new Host(2, InstanceType.M2, new SlotData(4, 0, 4)));
		store.add(new Host(3, InstanceType.M3, new SlotData(4, 0, 4)));

		// Full
		store.add(new Host(4, InstanceType.M1, new SlotData(3, 3, 0)));
		store.add(new Host(5, InstanceType.M2, new SlotData(3, 3, 0)));
		store.add(new Host(6, InstanceType.M3, new SlotData(3, 3, 0)));

		store.add(new Host(7, InstanceType.M1, new SlotData(2, 2, 0)));
		store.add(new Host(8, InstanceType.M2, new SlotData(2, 2, 0)));
		store.add(new Host(9, InstanceType.M3, new SlotData(2, 2, 0)));

		// Most Filled
		store.add(new Host(10, InstanceType.M1, new SlotData(10, 3, 7)));
		store.add(new Host(11, InstanceType.M2, new SlotData(3, 1, 2)));
		store.add(new Host(12, InstanceType.M3, new SlotData(5, 4, 1)));

		Statistics statistics = new AggregatedStatistics(store);

		String expectedReport = "EMPTY: M1=1; M2=1; M3=1;" + "\n" +
								"FULL: M1=2; M2=2; M3=2;" + "\n" +
								"MOST FILLED: M1=1,7; M2=1,2; M3=1,1;";

		Assert.assertEquals(expectedReport, statistics.report().toString());
	}

}
