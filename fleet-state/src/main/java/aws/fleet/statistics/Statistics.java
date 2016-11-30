package aws.fleet.statistics;

import aws.fleet.domain.Host;

/**
 *
 * @author rafaeldantas@gmail.com
 *
 */
public interface Statistics {

	void add(Host host);

	StringBuilder report();

}
