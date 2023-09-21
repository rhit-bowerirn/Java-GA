package graphing.plotting;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;

import graphing.plotting.canvas.CoordinatePlane;
import graphing.plotting.plots.Legend;
import graphing.plotting.plots.Plot;

public class PlotComponent extends JComponent {

    protected String plotTitle;
    protected String xAxisTitle;
    protected String yAxisTitle;
    protected CoordinatePlane canvas;
    protected List<Plot> plots;
    protected Legend legend;

    protected static final int PADDING_LEFT = 75;
    protected static final int PADDING_RIGHT = 25;
    protected static final int PADDING_Y = 75;

    public PlotComponent(String plotTitle, String xAxisTitle, String yAxisTitle, CoordinatePlane canvas) {
        this.plotTitle = plotTitle;
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
        this.canvas = canvas;
        this.plots = new ArrayList<Plot>();
        this.legend = new Legend();
        this.legend.clearPlots();
    }

    public void addPlot(Plot plot) {
        this.legend.addPlot(plot);
        this.plots.add(plot);
    }

    public void addPlots(Plot... plots) {
        this.plots.addAll(Arrays.asList(plots));
        this.legend.addPlots(plots);
    }

    public void removePlot(Plot plot) {
        this.legend.removePlot(plot);
        this.plots.remove(plot);
    }

    public void clearPlots() {
        this.legend.clearPlots();
        this.plots.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        this.canvas.scale(this.plots);

        int width = getWidth() - (PADDING_LEFT + PADDING_RIGHT);
        int height = getHeight() - PADDING_Y * 2;
        int plotWidth = (int) (width * 0.75 + .5);
        int legendWidth = (int) (width * .2 + .5);
        double xScale = this.canvas.xScale(plotWidth);
        double yScale = this.canvas.yScale(height);
        int plotOriginX = this.canvas.originX(plotWidth);
        int plotOriginY = this.canvas.originY(height);
        int plotWindowOriginX = plotWidth / 2 + PADDING_LEFT;
        int legendOriginX = PADDING_LEFT + width - legendWidth / 2;
        int originY = getHeight() / 2;

        this.drawTitles(g2d, plotWidth, height);
                
        g2d.translate(plotWindowOriginX, originY);

        this.canvas.drawOn(g2d, plotWidth, height);

        g2d.translate(plotOriginX, plotOriginY);
        g2d.scale(1, -1);
        for (Plot plot : this.plots) {
            plot.drawOn(g2d, xScale, yScale);
        }
        g2d.scale(1, -1);
        g2d.translate(-plotOriginX, -plotOriginY);

        g2d.translate(-plotWindowOriginX, -originY);

        this.drawLegend(g2d, legendOriginX, (int) originY, legendWidth, height);
    }

    private void drawTitles(Graphics2D g2d, int plotWidth, int height) {
        // Draw plot title
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 16f));
        int plotTitleWidth = g2d.getFontMetrics().stringWidth(this.plotTitle);
        g2d.drawString(this.plotTitle, getWidth() / 2 - plotTitleWidth / 2, PADDING_Y / 2);

        // Draw x-axis labels and title
        int xAxisTitleWidth = g2d.getFontMetrics().stringWidth(this.xAxisTitle);
        g2d.setFont(g2d.getFont().deriveFont(14f));
        g2d.drawString(this.xAxisTitle, PADDING_LEFT + plotWidth / 2 - xAxisTitleWidth / 2,
                getHeight() - PADDING_Y / 2);

        // Draw y-axis label (flipped 90 degrees)
        int yAxisTitleWidth = g2d.getFontMetrics().stringWidth(this.yAxisTitle);
        g2d.setFont(g2d.getFont().deriveFont(14f));
        g2d.translate(PADDING_Y / 2, PADDING_Y + height / 2 + yAxisTitleWidth / 2);
        g2d.rotate(-Math.PI / 2);
        g2d.drawString(this.yAxisTitle, 0, 0);
        g2d.rotate(Math.PI / 2);
        g2d.translate(-(PADDING_Y / 2), -(PADDING_Y + height / 2 + yAxisTitleWidth / 2));
    }

    private void drawLegend(Graphics2D g2d, int originX, int originY, int width, int height) {
        g2d.translate(originX, originY);
        this.legend.drawOn(g2d, width, height);
        g2d.translate(-originX, -originY);
    }

}
