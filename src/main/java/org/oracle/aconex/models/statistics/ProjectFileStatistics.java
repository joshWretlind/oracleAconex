package org.oracle.aconex.models.statistics;

import java.util.List;

public class ProjectFileStatistics extends AbstractFileStatistics {

    public ProjectFileStatistics() {
        addStatistic(new CustomerForGeoZoneStatistic());
        addStatistic(new CustomersForContractStatistic());
        addStatistic(new AverageBuildTimeForGeoZoneStatistic());
    }
}
