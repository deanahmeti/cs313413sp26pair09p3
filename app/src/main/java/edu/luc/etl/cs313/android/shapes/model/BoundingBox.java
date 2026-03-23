package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle) -pair 09 completed
        @Override
        public Location onCircle(final Circle c) {
            final int r = c.getRadius();
            return new Location(-r, -r, new Rectangle(2 * r, 2 * r));
        }

        @Override
        public Location onRectangle(final Rectangle r) {
            return new Location(0, 0, new Rectangle(r.getWidth(), r.getHeight()));
        }

        @Override
        public Location onLocation(final Location l) {
            final Location inner = l.getShape().accept(this);
            return new Location(
                    inner.getX() + l.getX(),
                    inner.getY() + l.getY(),
                    inner.getShape() // still a Rectangle
            );
        }

        @Override
        public Location onGroup(final Group g) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (final Shape s : g.getShapes()) {
                final Location box = s.accept(this);
                final int x = box.getX();
                final int y = box.getY();
                final Rectangle rect = (Rectangle) box.getShape();
                final int w = rect.getWidth();
                final int h = rect.getHeight();

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x + w);
                maxY = Math.max(maxY, y + h);
            }

            final int width = maxX - minX;
            final int height = maxY - minY;

            return new Location(minX, minY, new Rectangle(width, height));
        }

        @Override
        public Location onFill(final Fill f) {
            return f.getShape().accept(this);
        }

        @Override
        public Location onStrokeColor(final StrokeColor c) {
            return c.getShape().accept(this);
        }

        @Override
        public Location onOutline(final Outline o) {
            return o.getShape().accept(this);
        }

        @Override
        public Location onPolygon(final Polygon p) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (final Point pt : p.getPoints()) {
                minX = Math.min(minX, pt.getX());
                minY = Math.min(minY, pt.getY());
                maxX = Math.max(maxX, pt.getX());
                maxY = Math.max(maxY, pt.getY());
            }

            final int width = maxX - minX;
            final int height = maxY - minY;

            return new Location(minX, minY, new Rectangle(width, height));
        }
    }