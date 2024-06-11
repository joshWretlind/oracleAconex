package org.oracle.aconex.models;

public abstract class AbstractProjectRecord {
    enum BuildDurationType {
        SECONDS
    };
    public abstract boolean validateRecord();
}
