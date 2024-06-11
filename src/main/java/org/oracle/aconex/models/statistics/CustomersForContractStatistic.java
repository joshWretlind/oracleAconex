package org.oracle.aconex.models.statistics;

import org.oracle.aconex.models.AbstractProjectRecord;
import org.oracle.aconex.models.ProjectRecord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CustomersForContractStatistic implements Statistic {
    public static String STATISTIC_NAME = "CustomersForContract";

    Map<Integer, HashSet<Integer>> customersForContract = new HashMap<>();

    public void addRecord(AbstractProjectRecord record) {
        if (record instanceof ProjectRecord) {
            ProjectRecord projectRecord = (ProjectRecord) record;
            int contractId = projectRecord.getContractId();
            int customerId = projectRecord.getCustomerId();
            if (!customersForContract.containsKey(contractId)) {
                customersForContract.put(contractId, new HashSet<>());
            }
            customersForContract.get(contractId).add(customerId);
        }
    }

    public void getStatistic(Void v) {
        StringBuffer sb = new StringBuffer();
        sb.append(STATISTIC_NAME).append("\n");
        for (Map.Entry<Integer, HashSet<Integer>> entry : customersForContract.entrySet()) {
            sb.append("Contract ID: ")
                    .append(entry.getKey())
                    .append(", Customers: ")
                    .append(entry.getValue().size())
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    public Object getValue() {
        return customersForContract;
    }

    public String getName() {
        return STATISTIC_NAME;
    }
}
