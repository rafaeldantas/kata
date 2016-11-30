package aws.fleet.parser;

import aws.fleet.domain.Host;

/**
 * Parses a String line into a {@link Host}
 *
 * @author rafael
 *
 */
public interface LineParser {

	Host parseLine(String line);
}
