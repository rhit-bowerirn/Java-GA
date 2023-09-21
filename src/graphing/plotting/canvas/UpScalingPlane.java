package graphing.plotting.canvas;

import java.util.List;

import graphing.plotting.plots.Plot;

public class UpScalingPlane extends CoordinatePlane {

    public UpScalingPlane() {
        super();
    }

    public UpScalingPlane(double xMin, double xMax, double yMin, double yMax) {
        super(xMin, xMax, yMin, yMax);
    }

    public void scale(List<Plot> plots) {
        if(plots.isEmpty()) {
            this.resetScale();
            return;
        }

        double xMin = this.xMin;
        double xMax = this.xMax;
        double yMin = this.yMin;
        double yMax = this.yMax;

        for (Plot plot : plots) {
            xMin = Math.min(xMin, plot.minX());
            xMax = Math.max(xMax, plot.maxX());
            yMin = Math.min(yMin, plot.minY());
            yMax = Math.max(yMax, plot.maxY());
        }

        this.setScale(xMin, xMax, yMin, yMax);
    }

}
