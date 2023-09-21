package ga.viz.control;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileIO.CSVChooser;
import ga.sim.alg.GeneticAlgorithm;

public class DefaultAlgController extends AlgorithmController {

    public DefaultAlgController(GeneticAlgorithm ga) {
        super(ga);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel generationsLabel = new JLabel("Generations");
        JTextField generationsInput = new JTextField("1000", 5);
        JButton runFor = new JButton("Run For ");
        runFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int generations = Integer.parseInt(generationsInput.getText());
                    run(generations);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton run = new JButton("Run");
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });

        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JButton log = new JButton("Log to CSV");
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = new CSVChooser().selectFile("log.csv");
                    if (selectedFile == null) {
                        return;
                    }
                    logCSV(selectedFile.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, "Successfully logged data to " + selectedFile.getName(),
                            "Success!",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        controlPanel.add(run);
        controlPanel.add(stop);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(runFor);
        controlPanel.add(generationsInput);
        controlPanel.add(generationsLabel);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(reset);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(log);

        this.add(controlPanel);
    }

}
