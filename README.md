# A Java library for Genetic Algorithms

## Usage
All that you need to do is extend the `Genome` abstract class and implement the `fitness()`, `crossover(Genome other)`, `mutate(double mutationRate)`, and `toString()` abstract methods. Creating a custom `SelectionMethod` and custom `Visualization`s is also possible for more customization.

### Genetic Algorithm
| Parameter | Type | Description |
|-|-|-|
| initialPopulation | java.util.Collection<Genome> | An initial collection of `ga.sim.alg.Genome` objects to optimize |
| selectionMethod | ga.sim.selection.SelectionMethod | The selection method to be used for selecting parents. Built in options include `SelectByProportion`, `SelectByRank`, `SelectByRoulette`, `SelectByTournament`, `SelectByTruncation` |
| mutationRate | double | The rate at which mutation should occur |
| eliteCount | int | The number of fittest genomes to survive to the next generation |
| rand | java.util.Random | A seeded random object to allow for reproducible results |


