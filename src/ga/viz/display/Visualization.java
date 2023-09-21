package ga.viz.display;

import javax.swing.JComponent;

import ga.sim.alg.GeneticAlgorithm;
import ga.sim.alg.Observer;

public abstract class Visualization implements Observer {

    @Override
    public final void update(GeneticAlgorithm ga) {
        this.updateComponent(ga);
    }

    @Override
    public final void reset(GeneticAlgorithm ga) {
        this.clearComponent();
        this.update(ga);
    }

    public abstract JComponent visualization();

    public abstract String name();

    protected abstract void updateComponent(GeneticAlgorithm ga);

    protected void clearComponent() {
        //default to do nothing
    };

}
