package ga.sim.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;

public class SelectByRank implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        // sort the genomes so we can use their rank
        population.sort(Genome::compareTo);
        List<Genome> children = new ArrayList<Genome>(population.size());

        // assume we get the full population
        for (int i = 0; i < population.size() / 2; i++) {
            // select a random parent
            int firstParent = this.randomRankedIndex(population.size(), rand);

            // sample again without replacement
            int secondParent = this.randomRankedIndex(population.size() - 1, rand);
            if (firstParent == secondParent) {
                secondParent++;
            }

            children.add(population.get(firstParent).crossover(population.get(secondParent)));
            children.add(population.get(secondParent).crossover(population.get(firstParent)));
        }

        return children;
    }

    private int randomRankedIndex(int populationSize, Random rand) {
        // Expand the population to find a ranked index
        int randomChoice = rand.nextInt((populationSize * (populationSize + 1)) / 2);

        // index(x) = floor(sqrt(2x + 1/4) - 1/2) maps x to the correct index
        // this makes it so the fittest genome has the highest probability
        return (int) (Math.sqrt((randomChoice * 2) + 0.25) - 0.5);
    }

    @Override
    public String toString() {
        return "Ranked Selection";
    }

}
