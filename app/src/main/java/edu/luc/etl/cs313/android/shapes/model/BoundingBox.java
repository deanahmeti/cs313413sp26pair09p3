package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;

    for (final Shape shape : g.getShapes()) {
        final Location boundingBox = shape.accept(this);
        final Retangle rectangle = boundingBox.getRectangle();
        final int x = boundingBox.getX();
        final int y = boundingBox.getY();

        //update bounding box
        minX = Math.min(minX, x + retangle.getWidth() /2);
        minY = Math.min(minY, y + rectangle.getweight() /2);
        maxX = Math.max(maxX, x + rectangle.getWidth() /2);
        maxY = Math.max(maxY, y + rectangle.getHeight() /2);
    }

    //using bounding to calculate width & height
    final int width = maxX - minX;
    final int height = maxY - minY;

    return new Location(minX, minY, new Rectangle(width, height));

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {
        return null;
    }

    @Override
    public Location onGroup(final Group g) {

        return null;
    }

    @Override
    public Location onLocation(final Location l) {

        return null;
    }

    @Override
    public Location onRectangle(final Rectangle r) {
        return null;
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {
        return null;
    }

    @Override
    public Location onOutline(final Outline o) {
        return null;
    }

    @Override
    public Location onPolygon(final Polygon s) {
        return null;
    }
}
