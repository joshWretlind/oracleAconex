package org.oracle.aconex.models;

public class ProjectRecord extends AbstractProjectRecord{
    //Assumed this will always be a int
    private int customerId;
    //Assumed this will always be a int
    private int contractID;
    // This could probably be a ENUM
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private int buildDuration;
    //As of right now, assumed buildDuration will always be in seconds. Code already potentially supports different units for build duration
    private BuildDurationType buildDurationType = BuildDurationType.SECONDS;

    public ProjectRecord(String[] recordSplit) {
        if(recordSplit.length != 6) {
            System.out.println("Invalid record format: " + recordSplit.length);
            throw new IllegalArgumentException("Invalid record format");
        }
        this.customerId = Integer.parseInt(recordSplit[0].trim());
        this.contractID = Integer.parseInt(recordSplit[1].trim());
        this.geoZone = recordSplit[2].trim();
        this.teamCode = recordSplit[3].trim();
        this.projectCode = recordSplit[4].trim();
        String buildDurationString = recordSplit[5].trim();
        if(buildDurationString.charAt(buildDurationString.length() -1) == 's') {
            this.buildDurationType = BuildDurationType.SECONDS;
            buildDurationString = buildDurationString.substring(0, buildDurationString.length() - 1);
        }
        this.buildDuration = Integer.parseInt(buildDurationString);
    }

    @Override
    public boolean validateRecord() {
        // Return true for now, placeholder for being able to validate a record
        return true;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Contract ID: " + contractID + ", Geo Zone: " + geoZone + ", Team Code: " + teamCode + ", Project Code: " + projectCode + ", Build Duration: " + buildDuration + " " + buildDurationType + "\n";
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getContractId() {
        return contractID;
    }

    public String getGeoZone() {
        return geoZone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getBuildDuration() {
        return buildDuration;
    }
}
