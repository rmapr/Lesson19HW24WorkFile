package hw24FileLoggerV2;

import hw24FileLoggerV2.entity.FileLoggerConfiguration;
import hw24FileLoggerV2.entity.InitPathFileName;
import hw24FileLoggerV2.entity.LoggingLevel;
import hw24FileLoggerV2.service.FileLogger;
import hw24FileLoggerV2.entity.FileLoggerConfigurationLoader;
import hw24FileLoggerV2.service.WorkFile;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        InitPathFileName initPathFileName = new InitPathFileName();
        WorkFile workFile =new WorkFile();

        LoggingLevel lvl = LoggingLevel.DEBUG;
        String message = "Write first record in log file";
        String fileName = initPathFileName.getLogFile();
        FileLoggerConfiguration fileLoggerConfiguration =new FileLoggerConfiguration(fileName, lvl, 128, "");
        FileLogger fileLogger = new FileLogger(fileLoggerConfiguration);

        fileLogger.infoDebug(message);
        Thread.sleep(1000);

        message = "Write in the fileConfig";
        FileLoggerConfigurationLoader fileLoggerConfigurationLoader = new FileLoggerConfigurationLoader(initPathFileName.getFileConfig());
        fileLoggerConfiguration = fileLoggerConfigurationLoader.load(message);
        fileLogger = new FileLogger(fileLoggerConfiguration);
        fileLogger.setActiveLoader(true);
        fileLogger.infoDebug(message);
        Thread.sleep(1000);

        lvl = LoggingLevel.INFO;
        message = "Write record ";
        fileLoggerConfiguration =new FileLoggerConfiguration(fileName, lvl, 256, "");
        fileLogger = new FileLogger(fileLoggerConfiguration);

        fileLogger.infoDebug(message);

        workFile.printContentsFile(initPathFileName.getLogFile());
    }
}
