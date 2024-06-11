package org.oracle.aconex.models.statistics;

import org.oracle.aconex.models.AbstractProjectRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractFileStatistics {
    List<Statistic> statisticList = new ArrayList<>();
    Map<String, Statistic> statisticMap = new HashMap<>();
    public void addRecord(AbstractProjectRecord record) {
        for(Statistic s : statisticList) {
            s.addRecord(record);
        }
    }

    public void generateReport() {
        for(Statistic s : statisticList) {
            s.getStatistic(null);
        }
    }

    void addStatistic(Statistic s){
        statisticList.add(s);
        statisticMap.put(s.getName(), s);
    }

    public Statistic getStatistic(String name){
        return statisticMap.get(name);
    }
}
