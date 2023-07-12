package hw24FileLogger.exception;

public class FileMaxSizeReachedException extends RuntimeException {
    public FileMaxSizeReachedException(String s) {
        super(s);
    }

}
