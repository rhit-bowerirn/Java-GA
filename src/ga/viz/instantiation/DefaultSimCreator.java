package ga.viz.instantiation;

import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import ga.sim.alg.GeneticAlgorithm;
import ga.sim.alg.Genome;
import ga.sim.selection.SelectionMethod;

public class DefaultSimCreator extends SimulationCreator {
    private SelectionMethodMenu selectionMethodMenu;
    private JTextField populationSizeInput;
    private JTextField mutationRateInput;
    private JTextField eliteCountInput;
    private JTextField randomSeedInput;
    private static final int TEXT_FIELD_LENGTH = 5;

    public DefaultSimCreator() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.selectionMethodMenu = new SelectionMethodMenu();
        this.add(new LabeledComponent("Selection Method: ", this.selectionMethodMenu, true));

        this.populationSizeInput = new JTextField("500", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Population Size: ", this.populationSizeInput, true));

        this.mutationRateInput = new JTextField("0.1", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Mutation Rate: ", this.mutationRateInput, true));

        this.eliteCountInput = new JTextField("1", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Elite Count: ", this.eliteCountInput, true));

        this.randomSeedInput = new JTextField("0", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Random Seed: ", this.randomSeedInput, true));
    }

    public GeneticAlgorithm initializeSimulation(GenomeCreator genomeCreator) throws Exception {
        SelectionMethod selectionMethod = this.selectionMethodMenu.selectionMethod();
        int populationSize;
        double mutationRate;
        int eliteCount;
        long randomSeed;
        List<Genome> population = new ArrayList<Genome>();

        try {
            populationSize = Integer.parseInt(this.populationSizeInput.getText());
            if (populationSize < 2) {
                throw new IllegalArgumentException("Population size must be at least 2 but was: " + populationSize);
            }
        } catch (Exception e) {
            throw new Exception("Population size must be an integer");
        }

        try {
            mutationRate = Double.parseDouble(this.mutationRateInput.getText());
            if (mutationRate < 0 || mutationRate > 1) {
                throw new IllegalArgumentException("Mutation rate must be between 0 and 1 but was: " + mutationRate);
            }
        } catch (Exception e) {
            throw new Exception("Mutation rate must be a decimal or fraction between 0 and 1");
        }

        try {
            eliteCount = Integer.parseInt(this.eliteCountInput.getText());
            if (eliteCount < 0) {
                throw new IllegalArgumentException("Elite count must be greater than 0 but was: " + eliteCount);
            }
            if (eliteCount >= populationSize) {
                throw new IllegalArgumentException(
                        "Elite count must be less than Population Size but was: " + mutationRate);
            }
        } catch (Exception e) {
            throw new Exception("Elite count must be an integer");
        }

        try {
            randomSeed = Long.parseLong(this.randomSeedInput.getText());
        } catch (Exception e) {
            throw new Exception("Random seed must be an integer");
        }

        Random rand = new Random(randomSeed);

        try {
            for (int i = 0; i < populationSize; i++) {
                population.add(genomeCreator.creatGenome(rand));
            }
        } catch (Exception e) {
            throw new Exception("Genome creation failed: " + e.getMessage());
        }

        return new GeneticAlgorithm(population, selectionMethod, mutationRate, eliteCount, rand);
    }
}
