package ga.sim.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;
import ga.sim.alg.PopulationUtil;

public class SelectByProportion implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        List<Genome> children = new ArrayList<Genome>(population.size());

        // so we can index based on fitness
        double totalFitness = PopulationUtil.totalFitness(population);

        //we need to shift it so 0 is the min fitness
        double zeroShift = Math.abs(PopulationUtil.minFitness(population));
        totalFitness += (zeroShift * population.size());

        // assume we get the full population
        for (int i = 0; i < population.size() / 2; i++) {
            // choose a random index based on the fitnesses
            double firstIndex = rand.nextDouble(totalFitness);

            // stochastic universal sampling for second index
            // choose the second point randomly to avoid hardset pairing
            double secondIndex = firstIndex + rand.nextDouble(0.25, 0.75) * totalFitness;
            if (secondIndex > totalFitness) {
                secondIndex -= totalFitness; // wrap to the beginning
            }
            
            Genome firstParent = null;
            Genome secondParent = null;

            for(Genome genome : population) {
                
                firstIndex -= (genome.fitness() + zeroShift);
                secondIndex -= (genome.fitness() + zeroShift);

                if(firstIndex <= 0 && firstParent == null) {
                    firstParent = genome;
                    if(secondParent != null) {
                        break;
                    }
                }

                if(secondIndex <= 0 && secondParent == null) {
                    secondParent = genome;
                    if(firstParent != null) {
                        break;
                    }
                }
            }

            children.add(firstParent.crossover(secondParent));
            children.add(secondParent.crossover(firstParent));
        }

        return children;
    }

    @Override
    public String toString() {
        return "Proportional Selection";
    }

}
