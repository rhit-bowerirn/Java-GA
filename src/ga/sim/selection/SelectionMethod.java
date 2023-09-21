package ga.sim.selection;

import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;

public interface SelectionMethod {
    List<Genome> nextGeneration(List<Genome> population, Random rand);
    String toString();
}
