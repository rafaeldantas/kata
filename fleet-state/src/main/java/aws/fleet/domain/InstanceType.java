package aws.fleet.domain;

import java.util.stream.Stream;

/**
 * A server Instance Type.
 *
 * @author rafael
 *
 */
public enum InstanceType {

	M1, M2, M3;

	public static Stream<InstanceType> stream() {
		return Stream.of(values());
	}
}
