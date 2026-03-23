package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle) -pair09 completed
    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas; // //initialize canvas for drawing
        this.paint = paint; //
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) {
        final int savedColor = paint.getColor(); //save current color of paint
        paint.setColor(c.getColor()); //setting stroke color to certain color
        c.getShape().accept(this);
        paint.setColor(savedColor); //restore original color

        return null;
    }

    @Override
    public Void onFill(final Fill f) {
        final Style savedStyle = paint.getStyle(); //save current style of paint
        paint.setStyle(Style.FILL); //setting style to FILL in order to fill the shape
        f.getShape().accept(this);
        paint.setStyle(savedStyle); //restore original

        return null;
    }

    @Override
    public Void onGroup(final Group g) {
        canvas.save(); //current transformation saved
        canvas.translate(g.getX(), g.getY());

        //draw all shapes within group
        for(final Shape shape : g.getShapes()) {
            shape.accept(this);
        }
        canvas.restore();
        return null;
    }

    @Override
    public Void onLocation(final Location l) {
        canvas.save(); //current transformation saved
        canvas.translate(l.getX(), l.getY());
        l.getShape().accept(this); //drawing underlying shape
        canvas.restore();

        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {

        //drawing a rectangle with given width + height
        canvas.drawRect(0, 0, r.getWidth(), r.getHeight(), paint);

        return null;
    }

    @Override
    public Void onOutline(Outline o) {
        final Style savedStyle = paint.getStyle(); //save current style of paint
        paint.setStyle(Style.STROKE); //set style to stroke
        o.getShape().accept(this);
        paint.setStyle(savedStyle); //restore

        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

        final float[] pts = new float[s.getPoints().size() * 4];
        int i = 0;
        for (final Point point : s.getPoints()) {
            pts[i++] = point.getX(); //start x line
            pts[i++] = point.getY(); //start y line
            pts[i++] = point.getX(); //end x line
            pts[i++] = point.getY(); //end y line
        }


        canvas.drawLines(pts, paint);
        return null;
    }
}
