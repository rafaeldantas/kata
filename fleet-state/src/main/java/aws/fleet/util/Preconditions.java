package aws.fleet.util;

import java.util.function.Function;

/**
 *
 * @author rafael
 *
 */
public class Preconditions {

	private Preconditions() {

	}

	public static void notNull(String message, Object... objects) {
		for (Object object : objects) {
			test(message, p -> object != null);
		}
	}

	public static void check(String message, boolean... values) {
		for (boolean object : values) {
			test(message, p -> object);
		}
	}

	public static void test(String message, Function<Void, Boolean> test) {
		if (!test.apply(null)) {
			throw new IllegalArgumentException(message);
		}
	}
}
