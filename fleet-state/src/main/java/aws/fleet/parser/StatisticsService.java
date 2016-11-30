package aws.fleet.parser;

import aws.fleet.statistics.Statistics;

/**
 * Reads lines as String from a input File
 *
 * @author rafael
 *
 */
public interface StatisticsService {

	Statistics generateStatisticsFromInputFile(String input);

	void writeStatisticsReport(Statistics statistics, String outputPath);

	void generateStatisticsReport(String inputPath, String outputPath);
}
