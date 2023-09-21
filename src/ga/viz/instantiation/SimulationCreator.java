package ga.viz.instantiation;

import javax.swing.JPanel;

import ga.sim.alg.GeneticAlgorithm;

public abstract class SimulationCreator extends JPanel {
    public abstract GeneticAlgorithm initializeSimulation(GenomeCreator genomeCreator) throws Exception;
}
