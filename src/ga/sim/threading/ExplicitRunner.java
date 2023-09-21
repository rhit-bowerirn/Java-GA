package ga.sim.threading;

import ga.sim.alg.GeneticAlgorithm;

public class ExplicitRunner extends AlgorithmRunner {
    private int generations;

    public ExplicitRunner(GeneticAlgorithm ga, int generations) {
        super(ga);
        this.generations = generations;
    }

    @Override
    public void run() {
        for (int i = 0; this.isRunning && i < this.generations; i++) {
            this.ga.nextGeneration();
        }
        this.isRunning = false;
    }
    
}
