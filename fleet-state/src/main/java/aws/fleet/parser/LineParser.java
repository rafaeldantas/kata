package aws.fleet.parser;

import aws.fleet.domain.Host;

/**
 * Parses a String line into a {@link Host}
 *
 * @author rafaeldantas@gmail.com
 *
 */
public interface LineParser {

	Host parseLine(String line);
}
