package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class Axis implements Drawable {
    private static final int AXIS_THICKNESS = 2; // Fixed pixels

    @Override
    public void draw(Graphics2D g2d, ViewTransform transform) {
        Dimension worldSize = transform.getWorldSize();

        Point2D origin = transform.worldToScreen(0, 0);
        Point2D xPos = transform.worldToScreen(worldSize.getWidth() / 2, 0);
        Point2D xNeg = transform.worldToScreen(-worldSize.getWidth() / 2, 0);
        Point2D yPos = transform.worldToScreen(0, worldSize.getHeight() / 2);
        Point2D yNeg = transform.worldToScreen(0, -worldSize.getHeight() / 2);

        // X Axis (Red)
        g2d.setStroke(new BasicStroke(AXIS_THICKNESS));
        g2d.setColor(Color.RED);
        g2d.drawLine((int)xNeg.getX(), (int)origin.getY(),
                (int)xPos.getX(), (int)origin.getY());

        // Y Axis (Green)
        g2d.setColor(Color.GREEN);
        g2d.drawLine((int)origin.getX(), (int)yNeg.getY(),
                (int)origin.getX(), (int)yPos.getY());
    }
}