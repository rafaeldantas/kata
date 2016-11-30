package aws.fleet.parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aws.fleet.domain.Host;
import aws.fleet.exception.FleetException;
import aws.fleet.statistics.AggregatedStatistics;
import aws.fleet.statistics.Statistics;
import aws.fleet.util.Logger;

/**
 * {@link StatisticsService} implementation. Reads an input file
 *
 * @author rafaeldantas@gmail.com
 *
 */
public class FileSystemStatisticsService implements StatisticsService {

	private final LineParser lineParser;

	public FileSystemStatisticsService(LineParser lineParser) {
		this.lineParser = lineParser;
	}

	/**
	 * Reads a file and returns its lines as a {@link List}
	 *
	 * @return the input file lines as {@link String}s
	 *
	 * @param File's
	 *            absolute path
	 */
	@Override
	public Statistics generateStatisticsFromInputFile(String input) {
		try {
			Logger.info("Parsing input file...");
			Set<Host> hosts = new HashSet<>();
			Files.readAllLines(Paths.get(input)).
				stream()
				.map(l -> lineParser.parseLine(l))
				.forEach(h -> {
				if (!hosts.add(h)) {
					throw new FleetException(String.format("Host with id {%s} already exists", h.id()));
				}
			});
			return new AggregatedStatistics(hosts);

		} catch (IOException e) {
			String msg = String.format("Could not generate Statistics from input file {%s}", input);
			Logger.error(msg);
			throw new FleetException(msg, e);
		}
	}


	@Override
	public void writeStatisticsReport(Statistics statistics, String outputPath) {

		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath));) {
			fileOutputStream.write(statistics.report().toString().getBytes());
		} catch (IOException e) {
			throw new FleetException(String.format("Could not write Statistics report to {%s}", outputPath), e);
		}

	}

	@Override
	public void generateStatisticsReport(String inputPath, String outputPath) {
		Statistics statistics = generateStatisticsFromInputFile(inputPath);
		writeStatisticsReport(statistics, outputPath);
	}

}
