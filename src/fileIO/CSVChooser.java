package fileIO;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class CSVChooser extends JFileChooser {

    public CSVChooser() {
        this.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return "CSV Files (*.csv)";
            }
        });
    }

    public File selectFile(String defaultFileName) throws Exception {
        this.setSelectedFile(new File(defaultFileName));
        return this.selectFile();
    }

    public File selectFile() throws Exception {
        int returnValue = this.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        throw new Exception("Failed to load file");
    }
}
