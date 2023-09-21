package ga.viz.control;
import java.awt.GridLayout;

import ga.viz.instantiation.GenomeCreator;
import ga.viz.instantiation.SimulationCreator;

public class DefaultConstController extends ConstantsController {

    public DefaultConstController(SimulationCreator simCreator, GenomeCreator genomeCreator) {
        super(simCreator, genomeCreator);
        this.setLayout(new GridLayout(1, 2));
        this.add(simCreator);
        this.add(genomeCreator);
    }

}
