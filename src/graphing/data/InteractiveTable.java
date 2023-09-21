package graphing.data;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fileIO.CSVChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InteractiveTable extends JComponent {
    private JTable table;
    private DefaultTableModel tableModel;

    public InteractiveTable() {
        tableModel = new DefaultTableModel(new String[] { "X", "Y" }, 0);
        table = new JTable(tableModel);

        JButton addButton = new JButton("Add Datapoint");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[] { "", "" });
            }
        });

        JButton removeButton = new JButton("Remove Datapoint");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = tableModel.getRowCount();
                if (rowCount > 0) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tableModel.removeRow(selectedRow);
                    } else {
                        tableModel.removeRow(rowCount - 1);
                    }
                }

            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        JButton loadCSV = new JButton("Load CSV File");
        loadCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = new CSVChooser().selectFile("log.csv");
                    if (selectedFile == null) {
                        return;
                    }

                    Dataset data = new Dataset();
                    int badLines = data.loadCSV(selectedFile.getAbsolutePath());
                    enterDataset(data);

                    if (badLines > 0) {
                        JOptionPane.showMessageDialog(null,
                                "Failed to load " + badLines + " lines of data from " + selectedFile.getName(),
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(loadCSV);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void clear() {
        tableModel.setRowCount(0);
    }

    public void enterDataset(Dataset data) {
        data.forEach(p -> tableModel.addRow(new Object[] { p.x, p.y }));
    }

    public Dataset toDataset() throws Exception {
        int rowCount = tableModel.getRowCount();
        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < rowCount; i++) {
            double x = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            double y = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            points.add(new Point(x, y));
        }

        return new Dataset(points);
    }
}
