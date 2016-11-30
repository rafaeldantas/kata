package aws.fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aws.fleet.parser.CsvParser;
import aws.fleet.parser.StatisticsService;
import aws.fleet.parser.FileSystemStatisticsService;
import aws.fleet.statistics.Statistics;

public class FileReaderTest {

	private StatisticsService service;

	@Before
	public void setup() {
		service = new FileSystemStatisticsService(new CsvParser());
	}

	@Test
	public void parseInputTest() throws IOException {

		String path = FileReaderTest.class.getClassLoader().getResource("input/FleetState.txt").getPath();
		InputStream resourceAsStream = FileReaderTest.class.getClassLoader()
				.getResourceAsStream("output/Statistics.txt");
		String statisticsAsString = new BufferedReader(new InputStreamReader(resourceAsStream)).lines()
				.collect(Collectors.joining("\n"));

		Statistics statistics = service.generateStatisticsFromInputFile(path);

		Assert.assertEquals(statisticsAsString, statistics.report().toString());
	}

}
