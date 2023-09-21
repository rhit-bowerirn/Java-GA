package graphing.plotting.canvas;

import java.util.List;

import graphing.plotting.plots.Plot;

public class StaticPlane extends CoordinatePlane {

    public StaticPlane(double xMin, double xMax, double yMin, double yMax) {
        super(xMin, xMax, yMin, yMax);
    }

    public void scale(List<Plot> plots) {
        // do nothing, we don't scale
    }

}
