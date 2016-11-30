package aws.fleet.statistics;

import java.util.Set;
import java.util.function.BiFunction;

import aws.fleet.domain.Host;
import aws.fleet.domain.InstanceType;
import aws.fleet.util.Logger;

/**
 * Aggregates empty, full and mostFilled reports into one
 *
 * @author rafael
 *
 */
public class AggregatedStatistics implements Statistics {

	//Mi=<count>
	private final BiFunction<HostStatistics, InstanceType, StringBuilder> defaultReportTemplate = (s, i) -> {
		return new StringBuilder(i.toString())
			.append("=")
			.append(s.instanceCount(i));
	};

	//Mi=<count>,<empty slots>
	private final BiFunction<HostStatistics, InstanceType, StringBuilder> emptyCountReportTemplate = (s, i) -> {
		return defaultReportTemplate
				.apply(s, i)
				.append(",")
				.append(s.emptyCount(i));
	};

	private final Statistics empty;
	private final Statistics full;
	private final Statistics mostFilled;

	public AggregatedStatistics(Set<Host> hosts) {
		this.empty = new HostStatistics("EMPTY", defaultReportTemplate);
		this.full = new HostStatistics("FULL", defaultReportTemplate);
		this.mostFilled = new HostStatistics("MOST FILLED", emptyCountReportTemplate);
		hosts.forEach(this::add);
	}

	public void add(Host host) {
		if (host.isEmpty()) {
			empty.add(host);
		} else if (host.isFull()) {
			full.add(host);
		} else {
			mostFilled.add(host);
		}
	}

	@Override
	public String toString() {
		return report().toString();
	}

	public StringBuilder report() {
		Logger.info("Aggregating reports ...");
		return empty.report().append("\n")
				.append(full.report()).append("\n")
				.append(mostFilled.report());
	}
}
