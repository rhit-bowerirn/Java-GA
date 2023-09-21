package graphing.data;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    public double x;
    public double y;

    public Point(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) this.x;
    }

    public int intY() {
        return (int) this.y;
    }

    public Point invert() {
        return new Point(this.y, this.x);
    }

    public static Comparator<Point> xComparator() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.x, p2.x);
            }       
        };
    }

    public static Comparator<Point> yComparator() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.y, p2.y);
            }
        };
    }

    public static Comparator<Point> vectorMagComparator() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(Math.sqrt(p1.x * p1.x + p1.y * p1.y), Math.sqrt(p2.x * p2.x + p2.y * p2.y));
            }       
        };
    }

    @Override
    public int compareTo(Point o) {
        int xComp = Point.xComparator().compare(this, o);
        int yComp = Point.yComparator().compare(this, o);
        return xComp != 0 ? xComp : yComp;
    }

    public boolean equals(Object o) {
        return Double.compare(this.x, ((Point) o).x) == 0 && Double.compare(this.y, ((Point) o).y) == 0;
    }
}
