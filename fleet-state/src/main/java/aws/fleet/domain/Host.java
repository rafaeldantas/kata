package aws.fleet.domain;

import aws.fleet.util.Preconditions;

/**
 * Host implementation.
 *
 * <pre>
 * Each host has a fixed number of slots in which virtual instances can run. Each host can only run instances of a particular type, e.g. M1, M2, or M3
 * </pre>
 *
 * @author rafael
 *
 */
public class Host {

	private final int id;
	private final InstanceType instanceType;
	private final SlotData slots;

	public Host(int id, InstanceType instanceType, SlotData slots) {
		Preconditions.notNull("id, instanceType, and slots are required! ", id, instanceType, slots);
		this.id = id;
		this.instanceType = instanceType;
		this.slots = slots;
	}

	public final int id() {
		return id;
	}

	public boolean isEmpty() {
		return slots.busy() == 0;
	}

	public boolean isFull() {
		return slots.total() == slots.busy();
	}

	public final InstanceType instanceType() {
		return instanceType;
	}

	public final SlotData slots() {
		return slots;
	}

	@Override
	public String toString() {
		return "Host [id=" + id + ", instanceType=" + instanceType + ", slots=" + slots + "]";
	}

	// Equals and HashCode are not exactly OO, but I'm assuming that the host id
	// is the only thing that actually makes it comparable to other hosts. In
	// the sense that is ok to have multiple hosts, with the same
	// setup (Instance Type and Slots availability)

	@Override
	public int hashCode() {
		return new Integer(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Host other = (Host) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
