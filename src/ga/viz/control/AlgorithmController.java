package ga.viz.control;

import javax.swing.JPanel;

import ga.sim.alg.GeneticAlgorithm;

public abstract class AlgorithmController extends JPanel {
    protected GeneticAlgorithm ga;

    public AlgorithmController(GeneticAlgorithm ga) {
        this.ga = ga;
    }

    public final void run(int generations) {
        this.ga.run(generations);
    }

    public final void run() {
        this.ga.run();
    }

    public final void stop() {
        this.ga.stop();
    }

    public final void reset() {
        this.ga.reset();
    }

    public final void logCSV(String filename) throws Exception {
        this.ga.logCSV(filename);
    }

}
