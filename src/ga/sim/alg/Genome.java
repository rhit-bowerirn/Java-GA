package ga.sim.alg;

import java.util.Random;

public abstract class Genome implements Comparable<Genome> {
    protected Random rand;

    public Genome(Random rand) {
        this.rand = rand;
    }

    public abstract double fitness();

    public abstract Genome crossover(Genome other);
    
    public abstract void mutate(double mutationRate);
    
    public abstract String toString();

    @Override
    public int compareTo(Genome other) {
        return Double.compare(this.fitness(), other.fitness());
    }
}
