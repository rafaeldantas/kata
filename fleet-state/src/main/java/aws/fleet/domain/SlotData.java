package aws.fleet.domain;

import aws.fleet.util.Preconditions;

/**
 * Holds the amount of free and busy slots on a particular {@link Host}
 *
 * @author rafael
 *
 */
public class SlotData {

	private final long total;

	private final long busy;

	public SlotData(long total, long busy, long free) {
		//The 'free' amount of slots is not necessary, but used for validating the input
		Preconditions.check(String.format("Invalid number of slots."),
				total >= busy,
				total >= free,
				busy + free == total);
		this.total = total;
		this.busy = busy;
	}

	public final long total() {
		return total;
	}

	public final long busy() {
		return busy;
	}

	public final long free() {
		return total - busy;
	}

	@Override
	public String toString() {
		return "SlotData [total=" + total + ", busy=" + busy + "]";
	}

}
