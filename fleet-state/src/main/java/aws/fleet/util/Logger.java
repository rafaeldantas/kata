package aws.fleet.util;

//Temporary, needs proper logging setup
public class Logger {

	public static void info(String message) {
		System.out.println("INFO: " + message);
	}

	public static void error(String message) {
		System.out.println("ERROR: " + message);
	}

}
