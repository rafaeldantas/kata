package aws.fleet;

import java.io.File;

import aws.fleet.parser.CsvParser;
import aws.fleet.parser.FileSystemStatisticsService;
import aws.fleet.util.Logger;
import aws.fleet.util.Preconditions;

public class Main {
	public static void main(String[] args) {
		Preconditions.check("Invalid arguments. Usage: --input /path/to/input.txt --output /path/to/statistics.txt",
				args.length == 4);

		String input = null;
		String output = null;
		for (int i = 0; i < args.length; i++) {
			if ("--input".equals(args[i])) {
				input = args[i + 1];
			}
			if ("--output".equals(args[i])) {
				output = args[i + 1];
			}
		}

		Logger.info(String.format("Generating report from input {%s} to {%s}", input, output));
		new FileSystemStatisticsService(new CsvParser()).generateStatisticsReport(input, output);
		Logger.info(String.format("Done, check %s", new File(output).getAbsolutePath()));
	}
}
