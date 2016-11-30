package aws.fleet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;
import aws.fleet.domain.SlotData;
import aws.fleet.parser.CsvParser;
import aws.fleet.parser.LineParser;

public class LineParserTest {

	private LineParser parser;

	@Before
	public void setup() {
		parser = new CsvParser();
	}

	@Test
	public void hostParseTest() {
		assertEquals(new Host(10, InstanceType.M1, new SlotData(4, 0, 4)), parser.parseLine("10,M1,4,0,0,0,0"));
		assertEquals(new Host(3, InstanceType.M2, new SlotData(4, 1, 3)), parser.parseLine("3,M2,4,0,0,1,0"));
		assertEquals(new Host(99, InstanceType.M3, new SlotData(6, 3, 3)), parser.parseLine("99,M3,6,1,0,1,0,0,1"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidHostTypeTest() {
		parser.parseLine("10,M4,4,0,0,0,0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidTotalSlotsTest() {
		parser.parseLine("10,M2,5,0,0,0,0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidNumberOfSlotsTest() {
		parser.parseLine("10,M2,4,0,0,0,0,1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidNumberOfLinesTest() {
		parser.parseLine("10,M2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyLineTest() {
		parser.parseLine(" ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFormatTest() {
		parser.parseLine("x,y,z,b");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidBooleanTest() {
		parser.parseLine("10,M1,4,0,0,0,x");
	}

	@Test
	public void inputParseTest() {
		assertEquals(new Host(10, InstanceType.M1, new SlotData(4, 0, 4)), parser.parseLine("10,M1,4,0,0,0,0"));
		assertEquals(new Host(16, InstanceType.M1, new SlotData(4, 3, 1)), parser.parseLine("16,M1,4,0,1,1,1"));
		assertEquals(new Host(18, InstanceType.M1, new SlotData(4, 0, 4)), parser.parseLine("18,M1,4,0,0,0,0"));
		assertEquals(new Host(19, InstanceType.M1, new SlotData(4, 4, 0)), parser.parseLine("19,M1,4,1,1,1,1"));
		assertEquals(new Host(20, InstanceType.M1, new SlotData(4, 1, 3)), parser.parseLine("20,M1,4,0,0,0,1"));
		assertEquals(new Host(21, InstanceType.M1, new SlotData(4, 3, 1)), parser.parseLine("21,M1,4,0,1,1,1"));
		assertEquals(new Host(22, InstanceType.M1, new SlotData(4, 2, 2)), parser.parseLine("22,M1,4,0,1,0,1"));
		assertEquals(new Host(23, InstanceType.M1, new SlotData(4, 3, 1)), parser.parseLine("23,M1,4,1,1,0,1"));
		assertEquals(new Host(77, InstanceType.M2, new SlotData(7, 7, 0)), parser.parseLine("77,M2,7,1,1,1,1,1,1,1"));
		assertEquals(new Host(78, InstanceType.M2, new SlotData(7, 5, 2)), parser.parseLine("78,M2,7,1,1,0,1,0,1,1"));
		assertEquals(new Host(79, InstanceType.M2, new SlotData(7, 6, 1)), parser.parseLine("79,M2,7,1,1,1,1,0,1,1"));
		assertEquals(new Host(80, InstanceType.M2, new SlotData(5, 4, 1)), parser.parseLine("80,M2,5,1,1,1,1,0"));
		assertEquals(new Host(81, InstanceType.M2, new SlotData(7, 6, 1)), parser.parseLine("81,M2,7,1,0,1,1,1,1,1"));
		assertEquals(new Host(82, InstanceType.M2, new SlotData(7, 2, 5)), parser.parseLine("82,M2,7,1,0,0,0,0,0,1"));
		assertEquals(new Host(83, InstanceType.M1, new SlotData(4, 3, 1)), parser.parseLine("83,M1,4,1,1,0,1"));
		assertEquals(new Host(84, InstanceType.M2, new SlotData(7, 5, 2)), parser.parseLine("84,M2,7,0,0,1,1,1,1,1"));
		assertEquals(new Host(87, InstanceType.M3, new SlotData(12, 9, 3)),
				parser.parseLine("87,M3,12,1,1,1,1,0,0,0,1,1,1,1,1"));
		assertEquals(new Host(89, InstanceType.M3, new SlotData(14, 11, 3)),
				parser.parseLine("89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1"));
		assertEquals(new Host(91, InstanceType.M3, new SlotData(14, 7, 7)),
				parser.parseLine("91,M3,14,1,0,0,0,0,0,1,0,0,1,1,1,1,1"));
		assertEquals(new Host(92, InstanceType.M1, new SlotData(4, 3, 1)), parser.parseLine("92,M1,4,1,1,0,1"));
		assertEquals(new Host(93, InstanceType.M3, new SlotData(14, 10, 4)),
				parser.parseLine("93,M3,14,0,0,1,1,1,1,1,0,0,1,1,1,1,1"));
	}

}
