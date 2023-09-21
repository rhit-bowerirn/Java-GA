package ga.viz.control;

import javax.swing.JPanel;

import ga.sim.alg.GeneticAlgorithm;
import ga.viz.instantiation.GenomeCreator;
import ga.viz.instantiation.SimulationCreator;
public abstract class ConstantsController extends JPanel {
    protected SimulationCreator simCreator;
    protected GenomeCreator genomeCreator;

    public ConstantsController(SimulationCreator simCreator, GenomeCreator genomeCreator) {
        this.simCreator = simCreator;
        this.genomeCreator = genomeCreator;
    }

    public GeneticAlgorithm startSimulation() throws Exception {
        return this.simCreator.initializeSimulation(this.genomeCreator);
    }
}
