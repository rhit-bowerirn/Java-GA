package ga.sim.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;
import ga.sim.alg.PopulationUtil;

public class SelectByTournament implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        List<Genome> children = new ArrayList<Genome>(population.size());

        // Ensure we always have at least 2, and scale linearly with population size
        //int numCompetitors = (int) (2.5 + population.size() / 100); // 2.5 to manually math.round
        
        //Apparently works better than scaling linearly
        int numCompetitors = 2;

        // We can do stochastic universal sampling to get lots of tournaments per shuffle and save time
        while (children.size() < population.size()) {
            Collections.shuffle(population, rand);

            for(int t = 0; t < population.size() - numCompetitors * 2; t += numCompetitors * 2) {
                int secondTournament = t + numCompetitors;
                Genome firstParent = PopulationUtil.fittestGenome(population.subList(t, secondTournament));
                Genome secondParent = PopulationUtil.fittestGenome(population.subList(secondTournament, secondTournament + numCompetitors));
                children.add(firstParent.crossover(secondParent));
                children.add(secondParent.crossover(firstParent));

                if(children.size() == population.size()) {
                    break;
                }
            }
        }

        return children;
    }

    @Override
    public String toString() {
        return "Tournament Selection";
    }

}
