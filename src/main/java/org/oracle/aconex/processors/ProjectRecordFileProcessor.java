package org.oracle.aconex.processors;

import org.oracle.aconex.models.ProjectFile;
import org.oracle.aconex.models.ProjectRecord;
import org.oracle.aconex.processors.interfaces.FileProcessorInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRecordFileProcessor implements FileProcessorInterface {
    // For now use a static delimeter
    private static String delimeter = ",";
    @Override
    public ProjectFile processFile(String filePath) {
        ProjectFile projectFile = new ProjectFile();
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(filePath));
            String line = inputStream.readLine();
            while(line != null) {
                String[] recordData = line.split(delimeter);
                try{
                    ProjectRecord newRecord = new ProjectRecord(recordData);
                    projectFile.addRecord(newRecord);
                } catch(NumberFormatException nfe) {
                    nfe.printStackTrace();
                } catch(IllegalArgumentException iae) {
                    // Ignore for now
                    System.out.println("Ignoring record: " + line);
                }
                line = inputStream.readLine();
            }
        } catch(FileNotFoundException fnfe) {
            //Ideally we would bring in a logging library to log out the exceptions, along with any correlated data
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return projectFile;
    }
}
