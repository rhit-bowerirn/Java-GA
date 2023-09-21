package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader extends FileHandler implements Loader {

    private BufferedReader reader;
    private String[] headers;

    public CSVLoader(String filename) throws Exception {
        super(filename);
        this.reader = new BufferedReader(new FileReader(filename));
        this.headers = this.next();
    }

    public String[] headers() {
        return headers;
    }

    @Override
    public String[] next() throws Exception {
        String line = reader.readLine();
        if (line != null) {
            return line.split(",");
        }
        return null; // No more lines to read
    }

    @Override
    public boolean hasNext() throws Exception {
        return reader.ready();
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
