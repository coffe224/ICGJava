package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class SplineControlPoint implements Interactable {
    private static final int HITBOX_SIZE = 40;
    private static final int CIRCLE_THICKNESS = 2;
    private static final Color DEFAULT_COLOR = Color.MAGENTA;
    private static final Color HOVERED_COLOR = Color.CYAN;

    private boolean isHovered = false;
    private Point2D originPoint;

    public SplineControlPoint(Point2D startPosition) {
        originPoint = startPosition;
    }

    @Override
    public void draw(Graphics2D g2d, ViewTransform transform) {
        Point2D screenPoint = transform.worldToScreen(originPoint.getX(), originPoint.getY());
        g2d.setStroke(new BasicStroke(CIRCLE_THICKNESS));
        if (isHovered) {
            g2d.setColor(HOVERED_COLOR);
        } else {
            g2d.setColor(DEFAULT_COLOR);
        }
        g2d.drawOval((int) screenPoint.getX(), (int) screenPoint.getY(), 1, 1);
        g2d.drawOval((int) screenPoint.getX() - HITBOX_SIZE / 2,
                (int) screenPoint.getY() - HITBOX_SIZE / 2,
                HITBOX_SIZE,
                HITBOX_SIZE);
    }

    @Override
    public Rectangle getHitbox(ViewTransform transform) {
        Point2D screenPoint = transform.worldToScreen(originPoint.getX(), originPoint.getY());

        return new Rectangle(
                (int)screenPoint.getX() - HITBOX_SIZE / 2,
                (int)screenPoint.getY() - HITBOX_SIZE / 2,
                HITBOX_SIZE,
                HITBOX_SIZE
        );
    }

    @Override
    public void move(double deltaX, double deltaY) {
        originPoint.setLocation(
                originPoint.getX() + deltaX,
                originPoint.getY() + deltaY
        );
    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
// implements interactable (позже)?