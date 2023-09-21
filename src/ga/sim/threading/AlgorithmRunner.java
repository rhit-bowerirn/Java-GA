package ga.sim.threading;

import ga.sim.alg.GeneticAlgorithm;

public abstract class AlgorithmRunner implements Runnable {
    protected boolean isRunning;
    protected GeneticAlgorithm ga;
    private Thread thread;

    public AlgorithmRunner(GeneticAlgorithm ga) {
        this.ga = ga;
        this.isRunning = false;
        this.thread = new Thread(this);
    }

    public void stop() {
        this.isRunning = false;
    }

    public void start() {
        if(!this.isRunning) {
            this.isRunning = true;
            this.thread.start();
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public abstract void run();
}
