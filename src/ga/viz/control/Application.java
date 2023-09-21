package ga.viz.control;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ga.sim.alg.GeneticAlgorithm;

public class Application extends JPanel {
    private ConstantsController constController;
    private VisualizationController vizController;

    public Application(ConstantsController constController, VisualizationController vizController) {

        this.constController = constController;
        this.vizController = vizController;
        this.setLayout(new BorderLayout());

        this.reset();
    }

    public void reset() {
        this.removeAll();
        this.add(constController, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GeneticAlgorithm ga = constController.startSimulation();
                    startSimulation(ga);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startPanel.add(startButton);
        this.add(startPanel, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    private void startSimulation(GeneticAlgorithm ga) {
        this.removeAll();

        JButton back = new JButton("Change Constants");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(back);

        this.add(vizController, BorderLayout.CENTER);
        this.add(new DefaultAlgController(ga), BorderLayout.SOUTH);
        this.add(backPanel, BorderLayout.NORTH);

        this.vizController.beginListening(ga);
        this.revalidate();
        this.repaint();
    }
}
