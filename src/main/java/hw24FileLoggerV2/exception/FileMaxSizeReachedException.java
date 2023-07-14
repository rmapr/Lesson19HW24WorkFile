package hw24FileLoggerV2.exception;

public class FileMaxSizeReachedException extends RuntimeException {
    public FileMaxSizeReachedException(String s) {
        super(s);
    }

}
