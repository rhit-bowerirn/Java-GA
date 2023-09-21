package ga.viz.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ga.viz.display.Visualization;

public class VisualizationSelector extends JPanel {
    private JPanel menuPanel;

    public VisualizationSelector(Visualization start, Visualization... visualizations) {
        this.menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenu visualizationMenu = new JMenu("Choose Visualization");
        JMenuItem noneOption = new JMenuItem("None");
        
        noneOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearVisualization();
            }
        });
        
        visualizationMenu.add(noneOption);

        for (Visualization visualization : visualizations) {
            JMenuItem item = new JMenuItem(visualization.name());

            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceVisualization(visualization);
                }
            });
            
            visualizationMenu.add(item);
        }

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(visualizationMenu);
        this.menuPanel.add(menuBar);
        
        this.add(menuPanel, BorderLayout.NORTH);


        if (start != null) {
            replaceVisualization(start);
        }
    }

    private void clearVisualization() {
        this.removeAll();
        this.add(menuPanel, BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }

    private void replaceVisualization(Visualization visualization) {
        this.clearVisualization();
        this.add(visualization.visualization(), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
