package ga.sim.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;

public class SelectByRoulette implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        List<Genome> children = new ArrayList<Genome>(population.size());

        for (int i = 0; i < population.size() / 2; i++) {
            // select a random parent
            int firstParent = rand.nextInt(population.size());

            // sample again without replacement
            int secondParent = rand.nextInt(population.size() - 1);
            if (secondParent == firstParent) {
                secondParent++;
            }

            children.add(population.get(firstParent).crossover(population.get(secondParent)));
            children.add(population.get(secondParent).crossover(population.get(firstParent)));
        }

        return children;
    }

    @Override
    public String toString() {
        return "Roulette Selection";
    }

}
