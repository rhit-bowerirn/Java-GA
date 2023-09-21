package graphing.plotting.plots;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import java.util.Iterator;

import graphing.data.Dataset;
import graphing.data.Point;

public class LinePlot extends Plot {

    private static final int DEFAULT_LINE_SIZE = 1;

    public LinePlot(String title, Color color) {
        super(title, color);
    }

    public LinePlot(String title, Color color, Dataset dataset) {
        super(title, color, dataset);
    }

    @Override
    public void drawOn(Graphics2D g2d, double xScale, double yScale) {

        if (this.dataset.isEmpty()) {
            return;
        }

        g2d.setColor(this.color);
        g2d.setStroke(new BasicStroke(DEFAULT_LINE_SIZE));

        Iterator<Point> iterator = this.dataset.iterator();

        if (this.dataset.size() == 1) {
            Point p = iterator.next();
            g2d.drawLine(0, 0, (int) (p.x * xScale + 0.5), (int) (p.y * yScale + 0.5));
            return;
        }

        Point p1 = iterator.next();
        while (iterator.hasNext()) {
            Point p2 = iterator.next();
            int x1 = (int) (p1.x * xScale + 0.5);
            int y1 = (int) (p1.y * yScale + 0.5);
            int x2 = (int) (p2.x * xScale + 0.5);
            int y2 = (int) (p2.y * yScale + 0.5);
            g2d.drawLine(x1, y1, x2, y2);

            p1 = p2;
        }

    }

    @Override
    public void drawLegendKey(Graphics2D g2d, int width, int height) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.setFont(g2d.getFont().deriveFont(height));

        int titleWidth = g2d.getFontMetrics().stringWidth(this.title);
        int spacing = (width - titleWidth) / 4;

        g2d.drawString(this.title, 0, 0);
        g2d.setColor(this.color);
        g2d.drawLine(titleWidth + spacing, -height / 4, width, -height / 4);
    }
}
