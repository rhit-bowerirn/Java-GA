package fileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CSVLogger extends FileHandler implements Logger {

    private BufferedWriter writer;

    public CSVLogger(String filename, String[] headers) throws Exception {
        super(filename);
        this.writer = new BufferedWriter(new FileWriter(this.file, true));
        this.log(headers);
    }

    @Override
    public void log(String[] log) throws Exception {
        this.writer.write(String.join(",", log) + "\n");
    }

    @Override
    public void close() throws Exception {
        this.writer.close();
    }
}
