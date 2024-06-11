package org.oracle.aconex.models.statistics;

import org.oracle.aconex.models.AbstractProjectRecord;
import org.oracle.aconex.models.ProjectRecord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CustomerForGeoZoneStatistic implements Statistic{
    public static String STATISTIC_NAME_COUNT = "CustomerCountForGeoZone";
    private static String STATISTIC_NAME_LIST = "CustomerListForGeoZone";

    Map<String, HashSet<Integer>> customersForGeoZone = new HashMap<>();

    public void addRecord(AbstractProjectRecord record) {
        if( record instanceof ProjectRecord){
            ProjectRecord projectRecord = (ProjectRecord) record;
            if(customersForGeoZone.containsKey(projectRecord.getGeoZone())){
                customersForGeoZone.get(projectRecord.getGeoZone()).add(projectRecord.getCustomerId());
            } else {
                HashSet<Integer> customers = new HashSet<>();
                customers.add(projectRecord.getCustomerId());
                customersForGeoZone.put(projectRecord.getGeoZone(), customers);
            }
        }
    }

    public void getStatistic(Void v) {
        StringBuffer countStringBuffer = new StringBuffer();
        StringBuffer listStringBuffer = new StringBuffer();
        countStringBuffer.append(STATISTIC_NAME_COUNT).append("\n");
        listStringBuffer.append(STATISTIC_NAME_LIST).append("\n");
        for (Map.Entry<String, HashSet<Integer>> entry : customersForGeoZone.entrySet()) {
            countStringBuffer.append("Geo Zone: ")
                    .append(entry.getKey())
                    .append(", Customer count: ")
                    .append(entry.getValue().size())
                    .append("\n");
            listStringBuffer.append("Geo Zone: ")
                    .append(entry.getKey())
                    .append(", Customer list: ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }
        System.out.println(countStringBuffer.toString());
        System.out.println(listStringBuffer.toString());
    }

    public Object getValue() {
        return customersForGeoZone;
    }

    public String getName() {
        return STATISTIC_NAME_COUNT;
    }
}
