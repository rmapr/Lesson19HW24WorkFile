package hw24FileLoggerV2;

import hw24FileLoggerV2.entity.FileLoggerConfiguration;
import hw24FileLoggerV2.entity.LoggingLevel;
import hw24FileLoggerV2.exception.FileMaxSizeReachedException;
import hw24FileLoggerV2.service.WorkFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerTest {

    @Test
    void crFileTest() throws IOException {
        File file = new WorkFile().crFile(new FileLoggerConfiguration("result1.log", LoggingLevel.INFO, 1024, "123"));
        assertTrue(file.exists());
    }
    @Test
    void checkMaxSizeTest() {
        WorkFile workFile = new WorkFile();
        assertTrue(workFile.checkMaxSize(255, 256));
    }
    @Test
    void checkMaxSizeExceptionTest() {
        WorkFile workFile = new WorkFile();
        assertThrowsExactly(FileMaxSizeReachedException.class, () -> workFile.checkMaxSize(512, 256));
    }
}