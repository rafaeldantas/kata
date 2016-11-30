package aws.fleet.statistics;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiFunction;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;

/**
 *
 * Per Host report type (EMPTY, FULL, MOST FILLED) Statistics
 *
 * @author rafaeldantas@gmail.com
 *
 */
public class HostStatistics implements Statistics {

	private final String name;
	private final Map<InstanceType, Counter> countStore;
	private final BiFunction<HostStatistics, InstanceType, StringBuilder> reportTemplate;

	public HostStatistics(String name, BiFunction<HostStatistics, InstanceType, StringBuilder> reportTemplate) {
		this.name = name;
		this.countStore = new ConcurrentHashMap<>();
		this.reportTemplate = reportTemplate;
		InstanceType.stream().forEach(t -> this.countStore.put(t, new Counter()));
	}

	public void add(Host h) {
		countStore.get(h.instanceType()).increment(h);
	}

	public StringBuilder report() {
		StringBuilder report = new StringBuilder(name).append(":");
		InstanceType.stream().forEach(t -> {
			report.append(" ").append(reportTemplate.apply(this, t).append(";"));
		});
		return report;
	}

	public String name() {
		return name;
	}

	public long instanceCount(InstanceType type) {
		return countStore().get(type).instanceCount();
	}

	public long emptyCount(InstanceType type) {
		return countStore().get(type).emptyCount();
	}

	public Map<InstanceType, Counter> countStore() {
		return Collections.unmodifiableMap(countStore);
	}

	@Override
	public String toString() {
		return report().toString();
	}

	private static final class Counter {

		private final LongAdder instanceCount = new LongAdder();
		private final LongAdder emptyCount = new LongAdder();

		public void increment(Host host) {
			instanceCount.increment();
			emptyCount.add(host.slots().free());
		}

		public long instanceCount() {
			return instanceCount.longValue();
		}

		public long emptyCount() {
			return emptyCount.longValue();
		}

	}

}
