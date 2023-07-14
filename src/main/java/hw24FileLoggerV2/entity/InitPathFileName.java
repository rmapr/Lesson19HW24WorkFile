package hw24FileLoggerV2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InitPathFileName {
    private String logFile = "result.log";
    private String fileConfig = "fileConfig.txt";
}
