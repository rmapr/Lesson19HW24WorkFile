package hw24FileLogger.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
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
