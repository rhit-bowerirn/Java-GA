package graphing.plotting.plots;

import java.awt.Color;
import java.awt.Graphics2D;

import graphing.data.Dataset;

public abstract class Plot {
    protected String title;
    protected Color color;
    protected Dataset dataset;

    public Plot(String title, Color color) {
        this.title = title;
        this.color = color;
        this.dataset = new Dataset();
    }

    public Plot(String title, Color color, Dataset dataset) {
        this.title = title;
        this.color = color;
        this.dataset = dataset;
    }

    public void setData(Dataset dataset) {
        this.dataset = dataset;
    }

    public void clearData() {
        this.dataset.clear();
    }

    public Dataset dataset() {
        return this.dataset;
    }

    public String title() {
        return this.title;
    }

    public Color color() {
        return this.color;
    }

    public double minX() {
        return this.dataset.minX();
    }

    public double maxX() {
        return this.dataset.maxX();
    }

    public double minY() {
        return this.dataset.minY();
    }

    public double maxY() {
        return this.dataset.maxY();
    }

    // assumes the g2d is translated to the plot origin and positive y is up
    public abstract void drawOn(Graphics2D g2d, double xScale, double yScale);

    public abstract void drawLegendKey(Graphics2D g2d, int width, int height);
}
