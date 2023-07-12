package hw24FileLogger;

import hw24FileLogger.entity.FileLoggerConfiguration;
import hw24FileLogger.entity.LoggingLevel;
import hw24FileLogger.service.FileLogger;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String logFile = "result.log";

        LoggingLevel lvl = LoggingLevel.INFO;
        String message = "Write first record in log file";
//        Передаю пустий шаблон, бо він формується всередині
        FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration(logFile, lvl, 128, "");
        FileLogger fileLogger = new FileLogger(fileLoggerConfiguration);
        fileLogger.infoDebug(message);
        Thread.sleep(500);

        lvl = LoggingLevel.DEBUG;
        message = "Write second record";
        fileLoggerConfiguration = new FileLoggerConfiguration(logFile, lvl, 256, "");
        fileLogger = new FileLogger(fileLoggerConfiguration);
        fileLogger.infoDebug(message);
        Thread.sleep(500);

        lvl = LoggingLevel.DEBUG;
        message = "Write the third record ";
        fileLoggerConfiguration = new FileLoggerConfiguration(logFile, lvl, 512, "");
        fileLogger = new FileLogger(fileLoggerConfiguration);
        fileLogger.infoDebug(message);
        Thread.sleep(500);

        lvl = LoggingLevel.INFO;
        message = "Write the fourth record ";
        fileLoggerConfiguration = new FileLoggerConfiguration(logFile, lvl, 512, "");
        fileLogger = new FileLogger(fileLoggerConfiguration);
        fileLogger.infoDebug(message);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            try (FileReader fileReader = new FileReader(fileLogger.getPath().concat(File.separator).concat(logFile))) {
                int a;
                while ((a = fileReader.read()) != -1) {
                    stringBuilder.append((char) a);
                }
            }
            String text = stringBuilder.toString();
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
