package hw24FileLogger.service;

import hw24FileLogger.entity.FileLoggerConfiguration;
import hw24FileLogger.entity.LoggingLevel;
import hw24FileLogger.exception.FileMaxSizeReachedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger {

    private FileLoggerConfiguration fileLogConfig;
    private String path;

    public String getPath() {
        return path;
    }

    public FileLogger() {
    }

    public FileLogger(FileLoggerConfiguration fileLogConfig) {
        this.fileLogConfig = fileLogConfig;
        this.path = "C:\\Hillel\\Project\\HomeTaskPro\\Lesson19HW24WorkFile\\src\\main\\java\\hw24FileLogger\\log";
    }

    public File crFile() throws IOException {
        String fileName = fileLogConfig.getFileName();
        String s = path;
        File file = new File(s, File.separator.concat(fileName));
//        System.out.println(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public String generateStringPattern(LoggingLevel lvl, String message) {
        String pattern = null;

        switch (lvl) {
            case INFO ->
                    pattern = String.format("%s | %s | ", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), lvl);
            case DEBUG ->
                    pattern = String.format("%s | %s | %s | ", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), lvl, LoggingLevel.INFO);
        }
        return pattern.concat("Повідомлення: ").concat(message).concat("\n");
    }

    public Boolean checkMaxSize(Integer fileSize, Integer maxFileSize) {
        if (fileSize >= maxFileSize) {
            throw new FileMaxSizeReachedException("\nMax size: " + maxFileSize +
                    "\nCurrent size: " + fileSize +
                    "\nPath: " + path + "\n");
        }
        return true;
    }

    public void infoDebug(String message) throws IOException {
//        create new file
        File file = crFile();
        System.out.println("Розмір файла до: " + file.length());
//        check for exceeding the maximum value
        if (checkMaxSize((int) file.length(), fileLogConfig.getMaxFileSize())) {
            System.out.println("Successfully written bytes to the file");
        }
//        generation of a string pattern taking into account the level
        String formatPattern = String.format(generateStringPattern(fileLogConfig.getLevel(), message));
        fileLogConfig.setLogFormat(formatPattern);
//        System.out.println(formatPattern);
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
