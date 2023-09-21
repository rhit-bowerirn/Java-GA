package ga.sim.records;

import java.util.List;

import fileIO.CSVLogger;
import fileIO.Logger;
import ga.sim.alg.Genome;

import java.util.LinkedList;

public class PopulationHistory {
    private LinkedList<PopulationStats> history;
    private double highestEverFitness;
    private double lowestEverFitness;

    public PopulationHistory() {
        this.history = new LinkedList<PopulationStats>();
        this.reset();
    }

    public void reset() {
        this.history.clear();
        this.highestEverFitness = Double.MIN_VALUE;
        this.lowestEverFitness = Double.MAX_VALUE;
    }

    public boolean addRecord(List<Genome> population) {
        PopulationStats stats = new PopulationStats(population, this.history.size());
        this.highestEverFitness = Math.max(stats.maxFitness(), this.highestEverFitness);
        this.lowestEverFitness = Math.min(stats.minFitness(), this.lowestEverFitness);
        return this.history.add(stats);
    }

    public PopulationStats latest() {
        return this.history.getLast();
    }

    public PopulationStats first() {
        return this.history.getFirst();
    }

    public PopulationStats generation(int generation) {
        if (generation < 0) {
            return this.first();
        }

        if (generation > this.generations()) {
            return this.latest();
        }

        return this.history.get(generation);
    }

    public int generations() {
        return this.history.size() - 1;
    }

    public double highestEverFitness() {
        return this.highestEverFitness;
    }

    public double lowestEverFitness() {
        return this.lowestEverFitness;
    }

    public void toCSV(String filename) throws Exception {
        Logger logger = new CSVLogger(filename, new String[] {
            "Generations",
            "Max Fitness",
            "Min Fitness",
            "Avg Fitness",
            "Fittest Genome",
        });
        this.logHistory(logger);
        
    }

    private void logHistory(Logger logger) throws Exception {
        for (PopulationStats stats : this.history) {
            stats.log(logger);
        }
        logger.close();
    }
}
