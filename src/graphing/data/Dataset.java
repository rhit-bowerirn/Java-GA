package graphing.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList; //for concurrency safety with drawing
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fileIO.CSVLoader;
import fileIO.CSVLogger;
import fileIO.Logger;

public class Dataset extends CopyOnWriteArrayList<Point> {
    public Dataset() {
        super();
    }

    public Dataset(String csvName) throws Exception {
        super();
        this.loadCSV(csvName);
    }

    public Dataset(Collection<Point> data) {
        super(data);
    }

    public Dataset(Map<Double, Double> data) {
        super(loadFromMap(data));
    }

    public Dataset(List<Double> data, boolean isZeroIndexed) {
        super(loadFromList(data, (isZeroIndexed ? 0 : 1)));
    }

    public int loadCSV(String filename) throws Exception {
        CSVLoader loader = new CSVLoader(filename);
        int dimensions = loader.headers().length;
        if (dimensions < 1 || dimensions > 2) {
            throw new Exception("Invalid CSV Format");
        }

        int badLines = 0;

        while (loader.hasNext()) {
            String[] line = loader.next();
            try {
                if (dimensions == 1) {
                    this.add(Double.parseDouble(line[0]));
                } else {
                    this.add(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
                }
            } catch (Exception e) {
                badLines++;
            }
        }

        loader.close();
        return badLines;
    }

    public void toCSV(String filename) throws Exception {
        CSVLogger logger = new CSVLogger(filename, new String[] { "x", "y" });
        this.log(logger);
    }

    private void log(Logger logger) throws Exception {
        for (Point p : this) {
            logger.log(new String[] {
                    Double.toString(p.x),
                    Double.toString(p.y)
            });
        }
        logger.close();
    }

    public boolean addAll(Map<Double, Double> newData) {
        return this.addAll(loadFromMap(newData));
    }

    public boolean addAll(List<Double> newData, boolean isZeroIndexed) {
        return this.addAll(loadFromList(newData, this.size() + (isZeroIndexed ? 0 : 1)));
    }

    public boolean add(double x, double y) {
        return this.add(new Point(x, y));
    }

    public boolean add(double y) {
        return this.add(new Point(this.size(), y));
    }

    // only removes first one, not all duplicates
    public Point remove(double x, double y) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).compareTo(new Point(x, y)) == 0) {
                return this.remove(i);
            }
        }
        return null;
    }

    public List<Double> xValues() {
        return this.stream().map(p -> p.x).collect(Collectors.toList());
    }

    public List<Double> yValues() {
        return this.stream().map(p -> p.y).collect(Collectors.toList());
    }

    public double minX() {
        return this.stream().min(Point.xComparator()).orElse(new Point(0, 0)).x;
    }

    public double maxX() {
        return this.stream().max(Point.xComparator()).orElse(new Point(0, 0)).x;
    }

    public double minY() {
        return this.stream().min(Point.yComparator()).orElse(new Point(0, 0)).y;
    }

    public double maxY() {
        return this.stream().max(Point.yComparator()).orElse(new Point(0, 0)).y;
    }

    public void replaceData(Collection<Point> newData) {
        this.clear();
        this.addAll(newData);
    }

    public void replaceData(Map<Double, Double> newData) {
        this.clear();
        this.addAll(loadFromMap(newData));
    }

    public void replaceData(List<Double> newData, boolean isZeroIndexed) {
        this.clear();
        this.addAll(loadFromList(newData, (isZeroIndexed ? 0 : 1)));
    }

    public void sort() {
        this.sort(Point::compareTo);
    }

    public Dataset transform(Function<Point, Point> action) {
        return new Dataset(this.stream().map(action).collect(Collectors.toList()));
    }

    public Dataset invert() {
        return this.transform(p -> p.invert());
    }

    public void removeDuplicates() {
        this.sort();
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i - 1).compareTo(this.get(i)) == 0) {
                this.remove(i);
                i--;
            }
        }
    }

    private static List<Point> loadFromMap(Map<Double, Double> data) {
        List<Point> points = new CopyOnWriteArrayList<Point>();
        for (double key : data.keySet()) {
            double x = key;
            double y = data.get(key);
            points.add(new Point(x, y));
        }
        Collections.sort(points, Point.xComparator());
        return points;
    }

    private static List<Point> loadFromList(List<Double> data, int startIndex) {
        return IntStream.range(startIndex, data.size() + startIndex)
                .mapToObj(index -> new Point(index, data.get(index)))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }
}
