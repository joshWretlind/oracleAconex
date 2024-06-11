package org.oracle.aconex.processors;

import org.oracle.aconex.models.AbstractProjectFile;
import org.oracle.aconex.models.AbstractProjectRecord;
import org.oracle.aconex.models.ProjectFile;
import org.oracle.aconex.models.statistics.AbstractFileStatistics;
import org.oracle.aconex.models.statistics.ProjectFileStatistics;
import org.oracle.aconex.processors.interfaces.StatisticsProcessorInterface;

public class ProjectFileStatisticsProcessor implements StatisticsProcessorInterface {
    AbstractProjectFile file;
    AbstractFileStatistics statistics;
    public ProjectFileStatisticsProcessor(AbstractProjectFile file) {
        this.file = file;
        this.statistics = new ProjectFileStatistics();
    }

    public void process() {
        for(AbstractProjectRecord record: file.getRecords()) {
            if(record.validateRecord()) {
                statistics.addRecord(record);
            }
        }
    }

    public void generateReport(){
        statistics.generateReport();
    }

    public AbstractFileStatistics getStatistics() {
        return statistics;
    }
}
