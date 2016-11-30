package aws.fleet.statistics;

import aws.fleet.domain.Host;

/**
 *
 * @author rafael
 *
 */
public interface Statistics {

	void add(Host host);

	StringBuilder report();

}
