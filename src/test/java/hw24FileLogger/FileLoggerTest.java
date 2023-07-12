package hw24FileLogger;

import hw24FileLogger.entity.FileLoggerConfiguration;
import hw24FileLogger.entity.LoggingLevel;
import hw24FileLogger.exception.FileMaxSizeReachedException;
import hw24FileLogger.service.FileLogger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerTest {

    @Test
    void crFileTest() throws IOException {
        File file = new FileLogger(new FileLoggerConfiguration("result1.log", LoggingLevel.INFO, 1024, "123")).crFile();
        assertTrue(file.exists());
    }
    @Test
    void checkMaxSizeTest() {
        FileLogger fileLogger = new FileLogger();
        assertTrue(fileLogger.checkMaxSize(255, 256));
    }
    @Test
    void checkMaxSizeExceptionTest() {
        FileLogger fileLogger = new FileLogger();
        assertThrowsExactly(FileMaxSizeReachedException.class, () -> fileLogger.checkMaxSize(512, 256));
    }
}