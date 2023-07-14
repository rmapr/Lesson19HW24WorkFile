package hw24FileLoggerV2.entity;

import hw24FileLoggerV2.exception.FileMaxSizeReachedException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

@Getter
@Setter
@ToString
public class FileLoggerConfigurationLoader {
    private String fileConfig;

    public FileLoggerConfigurationLoader(String fileConfig) {
        this.fileConfig = fileConfig;
    }

    public FileLoggerConfiguration load(String message) throws IOException {
        File file = (new File(fileConfig).getAbsoluteFile());
        if (!file.exists()) {
            throw new FileMaxSizeReachedException("\n File " + file.getName() + " is not exist!");
        }
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                result.add(Arrays.toString(br.readLine().split("\n")));
            }
        }
        int i =0;

       FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration();
        for (String s : result) {
            int indexSeparator = s.indexOf(": ");
            switch (i) {
                case 0 -> fileLoggerConfiguration.setFileName(s.substring(indexSeparator + 2, s.indexOf(']')));
                case 1 -> fileLoggerConfiguration.setLevel(LoggingLevel.valueOf(s.substring(indexSeparator + 2, s.indexOf(']'))));
                case 2 -> fileLoggerConfiguration.setMaxFileSize(parseInt(s.substring(indexSeparator + 2, s.indexOf(']'))));
                case 3 -> fileLoggerConfiguration.setLogFormat(format(s.substring(indexSeparator + 2, s.indexOf(']')),
                        new SimpleDateFormat("yyyy-MM-dd 'Time' HH:mm:ss").format(new Date()),
                        fileLoggerConfiguration.getLevel(),
                        message.concat("\n")));
                default -> throw new IllegalStateException("Unexpected value: " + i);
            }
            i++;
        }
        return fileLoggerConfiguration;
   }
}
