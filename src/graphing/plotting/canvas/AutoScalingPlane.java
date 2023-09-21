package graphing.plotting.canvas;

import java.util.List;

import graphing.plotting.plots.Plot;

public class AutoScalingPlane extends CoordinatePlane {

    public AutoScalingPlane() {
        super();
    }

    public AutoScalingPlane(double xMin, double xMax, double yMin, double yMax) {
        super(xMin, xMax, yMin, yMax);
    }

    public void scale(List<Plot> plots) {
        if(plots.isEmpty()) {
            this.resetScale();
            return;
        }

        double xMin = plots.get(0).minX();
        double xMax = plots.get(0).maxX();
        double yMin = plots.get(0).minY();
        double yMax = plots.get(0).maxY();

        for (int i = 1; i < plots.size(); i++) {
            xMin = Math.min(xMin, plots.get(i).minX());
            xMax = Math.max(xMax, plots.get(i).maxX());
            yMin = Math.min(yMin, plots.get(i).minY());
            yMax = Math.max(yMax, plots.get(i).maxY());
        }

        this.setScale(xMin, xMax, yMin, yMax);
    }
}
