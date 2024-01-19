package edu.project4.transformations;

import edu.project4.records.Point;

public class HyperbolicTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = Math.sin(theta) / radius;
        double y = radius * Math.cos(theta);

        return new Point(x, y);
    }
}
