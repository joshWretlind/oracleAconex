package org.oracle.aconex.models.statistics;

import org.oracle.aconex.models.AbstractProjectRecord;

public interface Statistic {
    void addRecord(AbstractProjectRecord record);
    void getStatistic(Void v);
    Object getValue();
    String getName();
}
