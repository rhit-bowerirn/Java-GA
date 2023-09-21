package ga.viz.instantiation;

import java.util.Random;

import javax.swing.JPanel;

import ga.sim.alg.Genome;

public abstract class GenomeCreator extends JPanel {
    public abstract Genome creatGenome(Random rand) throws Exception;
}
