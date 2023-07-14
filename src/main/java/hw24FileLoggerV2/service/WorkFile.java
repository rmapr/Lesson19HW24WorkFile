package hw24FileLoggerV2.service;

import hw24FileLoggerV2.entity.FileLoggerConfiguration;
import hw24FileLoggerV2.entity.InitPathFileName;
import hw24FileLoggerV2.exception.FileMaxSizeReachedException;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

@Getter
@Setter
public class WorkFile {
//    public WorkFile() {
//    }

    public File crFile(FileLoggerConfiguration fileLoggerConfiguration) throws IOException {
        File file = new File(String.valueOf(Paths.get(fileLoggerConfiguration.getFileName())));
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    public Boolean checkMaxSize(Integer fileSize, Integer maxFileSize) {
        InitPathFileName initPathFileName = new InitPathFileName();

        if (fileSize >= maxFileSize) {
            throw new FileMaxSizeReachedException("\nMax size: " + maxFileSize +
                    "\nCurrent size: " + fileSize +
                    "\nPath: " + new File(initPathFileName.getLogFile()).getAbsoluteFile() + "\n");
        }
        return true;
    }
    public void printContentsFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
            try (FileReader fileReader = new FileReader(path)) {
                int a;
                while ((a = fileReader.read()) != -1) {
                    stringBuilder.append((char) a);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        String text = stringBuilder.toString();
            System.out.println(text);
    }
}
