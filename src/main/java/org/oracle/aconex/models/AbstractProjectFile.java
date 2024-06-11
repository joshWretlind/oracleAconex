package org.oracle.aconex.models;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProjectFile {
    private List<AbstractProjectRecord> records = new ArrayList<>();

    public void addRecord(AbstractProjectRecord record) {
        records.add(record);
    }

    public boolean validateFile() {
        //Alternatively, we could give the AbstractProjectfile a property of isValid
        // and as we add each record, we set isValid = isValid && record.validateRecord()
        // So that the flag flips if a 'invalid' record is added
        for (AbstractProjectRecord record : records) {
            if (!record.validateRecord()) {
                return false;
            }
        }
        return true;
    }

    public int getFileSize() {
        return records.size();
    };

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(AbstractProjectRecord record : records) {
            sb.append(record.toString());
        }
        return sb.toString();
    }

    public List<AbstractProjectRecord> getRecords() {
        return records;
    }
}
