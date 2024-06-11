package org.oracle.aconex.models.statistics;

import org.oracle.aconex.models.AbstractProjectRecord;
import org.oracle.aconex.models.ProjectRecord;

import java.util.HashMap;
import java.util.Map;

public class AverageBuildTimeForGeoZoneStatistic implements Statistic{
    public static String STATISTIC_NAME = "AverageBuildTimeForGeoZone";

    Map<String, Integer> totalBuildTimeForGeoZone = new HashMap<>();
    Map<String, Integer> countByGeoZone = new HashMap<>();

    public void addRecord(AbstractProjectRecord record) {
        if(record instanceof ProjectRecord){
            ProjectRecord projectRecord = (ProjectRecord) record;
            totalBuildTimeForGeoZone.put(projectRecord.getGeoZone(),
                    totalBuildTimeForGeoZone.getOrDefault(projectRecord.getGeoZone(), 0) + projectRecord.getBuildDuration());

            countByGeoZone.put(projectRecord.getGeoZone(),
                    countByGeoZone.getOrDefault(projectRecord.getGeoZone(), 0) + 1);
        }
    }

    public void getStatistic(Void v) {
        StringBuffer sb = new StringBuffer();
        sb.append(STATISTIC_NAME).append("\n");
        for (Map.Entry<String, Integer> entry : totalBuildTimeForGeoZone.entrySet()) {
            sb.append("Geo Zone: ")
                    .append(entry.getKey())
                    .append(", Average Build Time: ")
                    .append((double)entry.getValue() / countByGeoZone.get(entry.getKey()))
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    public Object getValue() {
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : totalBuildTimeForGeoZone.entrySet()) {
            result.put(entry.getKey(), (double)entry.getValue() / countByGeoZone.get(entry.getKey()));
        }
        return result;
    }

    public String getName() {
        return STATISTIC_NAME;
    }
}
