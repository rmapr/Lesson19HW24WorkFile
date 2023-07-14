package hw24FileLoggerV2.service;

import hw24FileLoggerV2.entity.FileLoggerConfiguration;
import hw24FileLoggerV2.entity.LoggingLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger {

    private FileLoggerConfiguration fileLogConfig;
    private boolean activeLoader;

    public void setActiveLoader(boolean activeLoader) {
        this.activeLoader = activeLoader;
    }

    public FileLogger() {
        this.activeLoader = false;
    }

    public FileLogger(FileLoggerConfiguration fileLogConfig) {
        this.fileLogConfig = fileLogConfig;
        this.activeLoader = false;
    }

    public String generateStringPattern(LoggingLevel lvl, String message) {
        String pattern = null;

        switch (lvl) {
            case INFO ->
                    pattern = String.format("%s | %s | ", new SimpleDateFormat("yyyy-MM-dd 'Time' HH:mm:ss").format(new Date()), lvl);
            case DEBUG ->
                    pattern = String.format("%s | %s | %s | ", new SimpleDateFormat("yyyy-MM-dd 'Time' HH:mm:ss").format(new Date()), lvl, LoggingLevel.INFO);
        }
        return pattern.concat("Повідомлення: ").concat(message).concat("\n");
    }

    public void infoDebug(String message) throws IOException {
//        create new file
        WorkFile workFile = new WorkFile();
        File file = workFile.crFile(fileLogConfig);
        System.out.println("Розмір файла до: " + file.length());
//        check for exceeding the maximum value
        if (workFile.checkMaxSize((int) file.length(), fileLogConfig.getMaxFileSize())) {
            System.out.println("Successfully written bytes to the file");
        }
//        generation of a string pattern taking into account the level
        String formatPattern;
        if (!activeLoader) {
            formatPattern = String.format(generateStringPattern(fileLogConfig.getLevel(), message));
            fileLogConfig.setLogFormat(formatPattern);
        } else {
            formatPattern = fileLogConfig.getLogFormat();
        }
//        write in log file
        try {
            Files.write(Paths.get(String.valueOf(file)),
                    formatPattern.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Розмір файла після: " + file.length());
    }
}
