package aws.fleet.parser;

import java.util.StringTokenizer;
import java.util.concurrent.atomic.LongAdder;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;
import aws.fleet.domain.SlotData;
import aws.fleet.util.Logger;
import aws.fleet.util.Preconditions;

/**
 * Parses a line from the Input  File with the format:
 * <blockquote>
 * 	HostID,InstanceType,N,Slot1State,Slot2State,…,SlotNState
 * </blockquote>
 *
 * <pre>
 * <b>HostID</b> is an integer
 * <b>InstanceType</b> can be M1, M2, or M3
 * <b>N</b> is the total number of slots on the machine
 * <b>SlotjState</b> is 0 if slot j is empty and 1 if it is occupied by an instance
 * </pre>
 *
 *
 * @author rafael
 *
 */
public class CsvParser implements LineParser {

	public Host parseLine(String line) {

		Preconditions.check("Empty line not allowed", line != null && !line.trim().isEmpty());

		final StringTokenizer csvTokenizer = new StringTokenizer(line, ",");

		Preconditions.check(String.format("Invalid input format. At least 4 values are required for parsing: %s", line),
				csvTokenizer.countTokens() > 3);

		Logger.info(String.format("Parsing line {%s}", line));

		final int hostId = Integer.parseInt(csvTokenizer.nextToken().trim());
		final InstanceType type = InstanceType.valueOf(csvTokenizer.nextToken().trim());
		final long totalSlots = Long.valueOf(csvTokenizer.nextToken().trim());

		final LongAdder busy = new LongAdder();
		final LongAdder free = new LongAdder();

		while (csvTokenizer.hasMoreTokens()) {
			String state = csvTokenizer.nextToken().trim();
			switch (state) {
			case "0":
				free.increment();
				break;
			case "1":
				busy.increment();
				break;
			default:
				throw new IllegalArgumentException(String.format("Invalid boolean [%s], sould be 0 or 1", state));
			}
		}

		SlotData slots = new SlotData(totalSlots, busy.longValue(), free.longValue());
		return new Host(hostId, type, slots);
	}

}
