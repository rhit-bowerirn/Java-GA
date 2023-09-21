package fileIO;

public interface Loader {
    String[] next() throws Exception;
    boolean hasNext() throws Exception;
    void close() throws Exception;
}
