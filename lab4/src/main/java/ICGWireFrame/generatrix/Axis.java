package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class Axis implements Drawable {
    private static final int AXIS_THICKNESS = 2; // Fixed pixels

    @Override
    public void draw(Graphics2D g2d, ViewTransform transform) {
        Point2D origin = transform.worldToScreen(0, 0);
        Point2D xPos = transform.worldToScreen(100, 0);
        Point2D xNeg = transform.worldToScreen(-100, 0);
        Point2D yPos = transform.worldToScreen(0, 100);
        Point2D yNeg = transform.worldToScreen(0, -100);

        // X Axis (Red)
        g2d.setStroke(new BasicStroke(AXIS_THICKNESS));
        g2d.setColor(Color.RED);
        g2d.drawLine((int)xNeg.getX(), (int)origin.getY(),
                (int)xPos.getX(), (int)origin.getY());

        // Y Axis (Green)
        g2d.setColor(Color.GREEN);
        g2d.drawLine((int)origin.getX(), (int)yNeg.getY(),
                (int)origin.getX(), (int)yPos.getY());

        // Labels (fixed font size)
        drawLabels(g2d, transform);
    }

    private void drawLabels(Graphics2D g2d, ViewTransform transform) {
        g2d.setColor(Color.WHITE);
        Font original = g2d.getFont();
        g2d.setFont(original.deriveFont(12f));

        Point2D xLabel = transform.worldToScreen(105, -1);
        Point2D yLabel = transform.worldToScreen(-1, 105);
        Point2D originLabel = transform.worldToScreen(0.5, -0.5);

        g2d.drawString("X", (int)xLabel.getX(), (int)xLabel.getY());
        g2d.drawString("Y", (int)yLabel.getX(), (int)yLabel.getY());
        g2d.drawString("(0,0)", (int)originLabel.getX(), (int)originLabel.getY());

        g2d.setFont(original);
    }
}