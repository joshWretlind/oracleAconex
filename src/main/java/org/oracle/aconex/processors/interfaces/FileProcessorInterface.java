package org.oracle.aconex.processors.interfaces;

import org.oracle.aconex.models.ProjectFile;

public interface FileProcessorInterface {

    public ProjectFile processFile(String filePath);
}
