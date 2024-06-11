package org.oracle.aconex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oracle.aconex.models.ProjectFile;
import org.oracle.aconex.models.statistics.*;
import org.oracle.aconex.processors.ProjectFileStatisticsProcessor;
import org.oracle.aconex.processors.ProjectRecordFileProcessor;

import java.util.Map;

public class ProjectFileStatisticsProcessorTest {
    @Test
    public void testCreateStatisticsSimple() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/simple.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(1,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(1,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(1.0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).get("geozone"));
    }

    @Test
    public void testCreateStatisticsSimpleWith2Records() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/simple2.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(1,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(2,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(1.5,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).get("geozone"));
    }

    @Test
    public void testCreateStatisticsEmpty() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/empty.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).size());
    }

    @Test
    public void testCreateStatisticsEmptyLines() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/emptyLines.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).size());
    }

    @Test
    public void testCreateStatisticsCleanInput() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/inputClean.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(3,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(2,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(4222.0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).get("eu_west"));
    }

    @Test
    public void testCreateStatisticsExtraSpacesInput() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/inputExtraSpaces.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(3,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(2,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(2216.0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).get("us_west"));
    }

    @Test
    public void testCreateStatisticsSkippedLines() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/skippedLines.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(3,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(2,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(3445.0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).get("us_east"));
    }

    @Test
    public void testCreateStatisticsTooManyValues() {
        ProjectFile file = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/tooManyValues.csv");
        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(file);
        processor.process();
        AbstractFileStatistics statistics = processor.getStatistics();
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomerForGeoZoneStatistic.STATISTIC_NAME_COUNT).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(CustomersForContractStatistic.STATISTIC_NAME).getValue()).size());
        Assertions.assertEquals(0,
                ((Map)statistics.getStatistic(AverageBuildTimeForGeoZoneStatistic.STATISTIC_NAME).getValue()).size());
    }
}
