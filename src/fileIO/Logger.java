package fileIO;

public interface Logger {
    void log(String[] log) throws Exception;
    void close() throws Exception;
}
