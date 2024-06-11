package org.oracle.aconex;

import org.junit.jupiter.api.Test;
import org.oracle.aconex.models.ProjectFile;
import org.oracle.aconex.processors.ProjectRecordFileProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectRecordFileProcessorTest {

    @Test
    public void testProcessFileClean() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/inputClean.csv");

        assertTrue(result.validateFile());
        assertEquals(5, result.getFileSize());
    }

    @Test
    public void testProcessFileExtraSpaces() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/inputExtraSpaces.csv");

        assertTrue(result.validateFile());
        assertEquals(5, result.getFileSize());
    }

    @Test
    public void testProcessEmptyFile() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/empty.csv");

        assertTrue(result.validateFile());
        assertEquals(0, result.getFileSize());
    }

    @Test
    public void testProcessFileEmptyLines() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/emptyLines.csv");

        assertTrue(result.validateFile());
        assertEquals(0, result.getFileSize());
    }

    @Test
    public void testProcessFileSkippedLines() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/skippedLines.csv");

        assertTrue(result.validateFile());
        assertEquals(5, result.getFileSize());
    }

    @Test
    public void testProcessFileTooManyValues() {
        ProjectFile result = new ProjectRecordFileProcessor().processFile("src/test/resources/test_data/tooManyValues.csv");

        assertTrue(result.validateFile());
        assertEquals(0, result.getFileSize());
    }
}
