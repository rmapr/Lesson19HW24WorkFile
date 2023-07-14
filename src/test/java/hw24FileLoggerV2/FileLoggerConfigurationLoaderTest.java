package hw24FileLoggerV2;

import hw24FileLoggerV2.entity.FileLoggerConfigurationLoader;
import hw24FileLoggerV2.exception.FileConfigParamException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerConfigurationLoaderTest {

    @Test
    void loadException() throws IOException {
        File sourceFile = new File("fileConfig.txt");
        File outputFile = new File("fileConfig2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        String line;
        String outputLine = "FILE: result.log";
        while ((line = reader.readLine()) != null) {
            if (!line.equals(outputLine)) {
                writer.write(line);
                writer.newLine();
            }
        }
        reader.close();
        writer.close();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);

        FileLoggerConfigurationLoader fileLoggerConfigurationLoader = new FileLoggerConfigurationLoader(sourceFile.getName());
        assertThrowsExactly(FileConfigParamException.class, () -> fileLoggerConfigurationLoader.load("Error"));
    }
}