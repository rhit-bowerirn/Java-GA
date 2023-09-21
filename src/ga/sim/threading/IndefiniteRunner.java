package ga.sim.threading;

import ga.sim.alg.GeneticAlgorithm;

public class IndefiniteRunner extends AlgorithmRunner {

    public IndefiniteRunner(GeneticAlgorithm ga) {
        super(ga);
    }

    @Override
    public void run() {
        while (this.isRunning) {
            this.ga.nextGeneration();
        }
    }
    
}
