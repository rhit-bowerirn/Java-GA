package ga.sim.alg;

public interface Observer {
    void update(GeneticAlgorithm ga);
    void reset(GeneticAlgorithm ga);
}
