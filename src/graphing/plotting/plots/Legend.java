package graphing.plotting.plots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Legend {

    protected static final double PADDING_SCALE = .1;
    protected List<Plot> plots;

    public Legend() {
        this.plots = new ArrayList<Plot>();
    }

    public Legend(List<Plot> plots) {
        this.plots = plots;
    }

    public boolean addPlot(Plot plot) {
        return this.plots.add(plot);
    }

    public void addPlots(Plot... plots) {
        this.plots.addAll(Arrays.asList(plots));
    }

    public void removePlot(Plot plot) {
        this.plots.remove(plot);
    }

    public void clearPlots() {
        this.plots.clear();
    }

    // assumes it is translated to the origin
    public final void drawOn(Graphics2D g2d, int width, int height) {
        // draw borders
        g2d.setColor(Color.BLACK);
        g2d.drawLine(-width / 2, -height / 2, width / 2, -height / 2);
        g2d.drawLine(-width / 2, height / 2, width / 2, height / 2);
        g2d.drawLine(-width / 2, -height / 2, -width / 2, height / 2);
        g2d.drawLine(width / 2, -height / 2, width / 2, height / 2);
        if (this.plots.isEmpty()) {
            return;
        }
        
        int offsetX = (int) (width * PADDING_SCALE / 2 + 0.5);
        int offsetY = (int) (height * PADDING_SCALE / 2 + 0.5);

        int targetTitleHeight = (height - offsetY) / plots.size() - offsetY;
        int titleHeight = Math.min(targetTitleHeight, 12);

        for (int i = 0; i < this.plots.size(); i++) {
            int xPosition = -width / 2 + offsetX;
            int yPosition = -height / 2 + ((titleHeight / 2 + offsetY) * (i + 1));

            g2d.translate(xPosition, yPosition);
            this.plots.get(i).drawLegendKey(g2d, width - (offsetX * 2), titleHeight);
            g2d.translate(-xPosition, -yPosition);
        }
    }

}
