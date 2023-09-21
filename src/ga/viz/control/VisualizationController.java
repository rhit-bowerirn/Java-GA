package ga.viz.control;

import javax.swing.JPanel;

import ga.sim.alg.GeneticAlgorithm;
import ga.viz.display.Visualization;

public abstract class VisualizationController extends JPanel {
    protected Visualization[] visualizations;

    public VisualizationController(Visualization... visualizations) {
        this.visualizations = visualizations;

    }

    public void beginListening(GeneticAlgorithm ga) {
        for (Visualization viz : this.visualizations) {
            ga.subscribe(viz);
        }
    }

}
