package graphing.plotting.plots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import graphing.data.Dataset;
import graphing.data.Point;

public class ScatterPlot extends Plot {
    private static final int DEFAULT_POINT_SIZE = 5;

    public ScatterPlot(String title, Color color) {
        super(title, color);
    }

    public ScatterPlot(String title, Color color, Dataset dataset) {
        super(title, color, dataset);
    }

    @Override
    public void drawOn(Graphics2D g2d, double xScale, double yScale) {
        g2d.setColor(this.color);
        int pointSize = DEFAULT_POINT_SIZE;

        Iterator<Point> iterator = this.dataset.iterator();
        while (iterator.hasNext()) {
            Point p = iterator.next();
            int x = (int) (p.x * xScale + 0.5);
            int y = (int) (p.y * yScale + 0.5);
            g2d.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
        }
    }

    @Override
    public void drawLegendKey(Graphics2D g2d, int width, int height) {
        int pointSize = height / 2;
        g2d.setColor(Color.BLACK);
        g2d.setFont(g2d.getFont().deriveFont(height));
        g2d.drawString(this.title, 0, 0);
        g2d.setColor(this.color);
        g2d.fillOval(width - pointSize, -pointSize, pointSize,
                pointSize);
    }

}
