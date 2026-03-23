package edu.luc.etl.cs313.android.shapes.model;

import java.util.List;

/**
 * A special case of a group consisting only of Points.
 *
 */
public final class Polygon extends Group {

    public Polygon(final Point... points) {
        super(points);
    }

    @SuppressWarnings("unchecked")
    public List<? extends Point> getPoints() {
        return (List<? extends Point>) getShapes();
    }

    @Override
    public <Result> Result accept(final Visitor<Result> v) {
        // TODO your job -pair09 worked on
        return super.accept(v); //ensure visitors works with polygons points
    }
}
