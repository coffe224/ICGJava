package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class WorldBorder implements Drawable {
    private static final int BORDER_THICKNESS = 2;

    @Override
    public void draw(Graphics2D g2d, ViewTransform transform) {
        Dimension worldSize = transform.getWorldSize();
        Point2D p1 = transform.worldToScreen((double) worldSize.width / 2, (double) worldSize.height / 2);
        Point2D p2 = transform.worldToScreen((double) worldSize.width / 2, (double) -worldSize.height / 2);
        Point2D p3 = transform.worldToScreen((double) -worldSize.width / 2, (double) -worldSize.height / 2);
        Point2D p4 = transform.worldToScreen((double) -worldSize.width / 2, (double) worldSize.height / 2);

        g2d.setStroke(new BasicStroke(BORDER_THICKNESS));
        g2d.setColor(Color.YELLOW);
        g2d.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
        g2d.drawLine((int)p2.getX(), (int)p2.getY(), (int)p3.getX(), (int)p3.getY());
        g2d.drawLine((int)p3.getX(), (int)p3.getY(), (int)p4.getX(), (int)p4.getY());
        g2d.drawLine((int)p4.getX(), (int)p4.getY(), (int)p1.getX(), (int)p1.getY());
    }
}
