package ga.sim.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;
import ga.sim.alg.PopulationUtil;


public class SelectByTruncation implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        int numParents = population.size() / 2;
        PopulationUtil.partialSort(population, numParents, rand);
        List<Genome> parents = new ArrayList<Genome>(numParents);

        // add the fittest 50% of genomes
        parents.addAll(population.subList(0, numParents));

        // run it twice to get a full population
        SelectionMethod roulette = new SelectByRoulette();
        List<Genome> children = roulette.nextGeneration(parents, rand);
        children.addAll(roulette.nextGeneration(parents, rand));

        return children;
    }

    @Override
    public String toString() {
        return "Truncation Selection";
    }
    
}
