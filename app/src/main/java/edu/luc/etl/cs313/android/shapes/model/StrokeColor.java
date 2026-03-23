package edu.luc.etl.cs313.android.shapes.model;

/**
 * A decorator for specifying the stroke (foreground) color for drawing the
 * shape.
 */
public final class StrokeColor implements Shape {

    // TODO entirely your job
    private final int color; //store color value
    private final Shape shape; //underlying shape will pair with a stroke color.

    public StrokeColor(final int color, final Shape shape) {
        this.color = color; //initialize stroke color
        this.shape = shape; //initialize underlyig shape
    }

    public int getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public <Result> Result accept(Visitor<Result> v) {
        return shape.accept(v); //ensures visitor will work on shape being colored.
    }
}
