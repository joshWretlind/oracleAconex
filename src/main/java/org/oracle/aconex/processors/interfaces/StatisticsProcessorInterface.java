package org.oracle.aconex.processors.interfaces;
import org.oracle.aconex.models.statistics.AbstractFileStatistics;

public interface StatisticsProcessorInterface {
    void generateReport();
    void process();
    AbstractFileStatistics getStatistics();
}
