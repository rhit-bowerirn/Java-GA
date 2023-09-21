package ga.sim.records;

import java.util.List;

import fileIO.Logger;
import ga.sim.alg.Genome;
import ga.sim.alg.PopulationUtil;

public class PopulationStats {
    private List<Genome> population;
    private int generation;
    private double minFitness;
    private double maxFitness;
    private double avgFitness;
    private Genome fittestGenome;

    public PopulationStats(List<Genome> population, int generation) {
        this.population = population;
        this.generation = generation;

        //maybe do this manually to save time?
        this.minFitness = PopulationUtil.minFitness(this.population);
        this.maxFitness = PopulationUtil.maxFitness(population);
        this.avgFitness = PopulationUtil.averageFitness(this.population);
        this.fittestGenome = PopulationUtil.fittestGenome(this.population);
    }

    public void log(Logger logger) throws Exception {
        logger.log(new String[] {
                Integer.toString(this.generation),
                Double.toString(this.maxFitness()),
                Double.toString(this.minFitness()),
                Double.toString(this.averageFitness()),
                this.fittestGenome().toString()
        });
    }

    public List<Genome> population() {
        return this.population;
    }

    public int generation() {
        return this.generation;
    }

    public double maxFitness() {
        return this.maxFitness;
    }

    public double minFitness() {
        return this.minFitness;
    }

    public double averageFitness() {
        return this.avgFitness;
    }

    public Genome fittestGenome() {
        return this.fittestGenome;
    }

}
