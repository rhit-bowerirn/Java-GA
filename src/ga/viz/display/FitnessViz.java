package ga.viz.display;

import java.awt.Color;

import javax.swing.JComponent;

import ga.sim.alg.GeneticAlgorithm;
import ga.sim.records.PopulationStats;
import graphing.plotting.PlotComponent;
import graphing.plotting.canvas.AutoScalingPlane;
import graphing.plotting.plots.LinePlot;
import graphing.plotting.plots.Plot;

public class FitnessViz extends Visualization {
    private PlotComponent plotComponent;
    private Plot maxFitnessPlot;
    private Plot minFitnessPlot;
    private Plot avgFitnessPlot;

    public FitnessViz() {
        super();
        this.maxFitnessPlot = new LinePlot("Max", new Color(40, 158, 35));
        this.minFitnessPlot = new LinePlot("Min", Color.RED);
        this.avgFitnessPlot = new LinePlot("Average", Color.BLUE);

        this.plotComponent = new PlotComponent("Fitness vs Time", "Generation", "Fitness", new AutoScalingPlane());
        this.plotComponent.addPlots(maxFitnessPlot, avgFitnessPlot, minFitnessPlot);
    }

    @Override
    public void updateComponent(GeneticAlgorithm ga) {
        PopulationStats stats = ga.latest();
        this.maxFitnessPlot.dataset().add(stats.maxFitness());
        this.minFitnessPlot.dataset().add(stats.minFitness());
        this.avgFitnessPlot.dataset().add(stats.averageFitness());

        this.plotComponent.repaint();
    }

    @Override
    public void clearComponent() {
        this.maxFitnessPlot.clearData();
        this.minFitnessPlot.clearData();
        this.avgFitnessPlot.clearData();
    }

    @Override
    public JComponent visualization() {
        return this.plotComponent;
    }

    @Override
    public String name() {
        return "Fitness Plot";
    }

}
