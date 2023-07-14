package hw24FileLoggerV2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileLoggerConfiguration {
    private String fileName;
    private LoggingLevel level;
    private Integer maxFileSize;
    private String logFormat;

    public FileLoggerConfiguration(String fileName, LoggingLevel level, Integer maxFileSize, String logFormat) {
        this.fileName = fileName;
        this.level = level;
        this.maxFileSize = maxFileSize;
        this.logFormat = logFormat;
    }

    public FileLoggerConfiguration() {
    }
}
