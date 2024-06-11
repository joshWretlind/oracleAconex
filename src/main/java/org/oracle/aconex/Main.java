package org.oracle.aconex;

import org.oracle.aconex.models.AbstractProjectFile;
import org.oracle.aconex.processors.ProjectFileStatisticsProcessor;
import org.oracle.aconex.processors.ProjectRecordFileProcessor;

public class Main {
    public static void main(String[] args) {
        AbstractProjectFile projectFile = new ProjectRecordFileProcessor()
                .processFile("src/test/resources/test_data/inputClean.csv");

        ProjectFileStatisticsProcessor processor = new ProjectFileStatisticsProcessor(projectFile);
        processor.process();
        processor.generateReport();
    }
}