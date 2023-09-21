package graphing.plotting.plots;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphing.data.Dataset;

public class Histogram extends Plot {
    private int bins;

    public Histogram(String title, Color color, int bins) {
        super(title, color);
        this.color = color;
        this.bins = bins;
    }

    public Histogram(String title, Color color, Dataset dataset, int bins) {
        super(title, color, dataset);
    }

    public Histogram(String title, Color color) {
        super(title, color);
        this.bins = 5;
    }

    public Histogram(String title, Color color, Dataset dataset) {
        super(title, color, dataset);
        this.bins = 5;
    }

    private List<List<Double>> createBins(List<Double> values, int numBins) {
        List<List<Double>> bins = new ArrayList<List<Double>>(numBins);
        for(int i = 0; i < numBins; i++) {
            bins.add(new ArrayList<Double>());
        }

        double min = Collections.min(values);
        double max = Collections.max(values);
        double binWidth = (max - min) / numBins;
        for (double val : values) {
            int bin = (int) ((val - min) / binWidth);
            if (bin > numBins - 1) {
                bin = numBins - 1;
            }

            bins.get(bin).add(val);
        }

        return bins;
    }

    @Override
    public double minX() {
        return this.dataset.minY();
    }

    @Override
    public double maxX() {
        return this.dataset.maxY();
    }

    @Override
    public double minY() {
        return 0;
    }

    @Override
    public double maxY() {
        List<List<Double>> bins = createBins(this.dataset.yValues(), this.bins);
        return bins.stream().mapToInt(bin -> bin.size()).max().orElse(0);
    }

    @Override
    public void drawOn(Graphics2D g2d, double xScale, double yScale) {
        g2d.setColor(this.color);

        List<List<Double>> bins = createBins(this.dataset.yValues(), this.bins);

        double start = this.minX();

        double binWidth = (this.maxX() - start) / this.bins;

        for (int i = 0; i < bins.size(); i++) {
            List<Double> bin = bins.get(i);
            g2d.fillRect((int) ((i * binWidth + start) * xScale) + 1, //So it doesn't overlap the y axis
                    0,
                    (int) (binWidth * xScale) + 1,
                    (int) (bin.size() * yScale));
        }     
    }

    @Override
    public void drawLegendKey(Graphics2D g2d, int width, int height) {
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.setFont(g2d.getFont().deriveFont(height));

        int titleWidth = g2d.getFontMetrics().stringWidth(this.title);
        int spacing = (width - titleWidth) / 4;

        g2d.drawString(this.title, 0, 0);
        g2d.setColor(this.color);
        g2d.drawLine(titleWidth + spacing, -height / 4, width, -height / 4);
    }
}
