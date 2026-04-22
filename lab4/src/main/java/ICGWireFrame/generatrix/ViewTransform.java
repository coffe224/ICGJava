package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class ViewTransform {
    private static final Dimension WORLD_SIZE = new Dimension(1000, 600);

    private double zoom;
    private double panX;
    private double panY;
    private Dimension viewSize;

    public ViewTransform() {
        this.zoom = 1.0;
        this.panX = 0;
        this.panY = 0;
        this.viewSize = new Dimension(800, 600);
    }

    public void setViewSize(Dimension size) {
        this.viewSize = size;
    }

    public void pan(double deltaX, double deltaY) {
        panX += deltaX;
        panX = Math.min(panX, WORLD_SIZE.getWidth() / 2);
        panX = Math.max(panX, -WORLD_SIZE.getWidth() / 2);

        panY += deltaY;
        panY = Math.min(panY, WORLD_SIZE.getHeight() / 2);
        panY = Math.max(panY, -WORLD_SIZE.getHeight() / 2);
    }

    public void reset() {
        zoom = 1.0;
        panX = 0;
        panY = 0;
    }

    public Point2D worldToScreen(double worldX, double worldY) {
        double screenX = (worldX - panX) * zoom + viewSize.width / 2.0;
        double screenY = viewSize.height / 2.0 - (worldY - panY) * zoom;
        return new Point2D.Double(screenX, screenY);
    }

    public Point2D screenToWorld(Point screenPoint) {
        double worldX = (screenPoint.getX() - viewSize.width / 2.0) / zoom + panX;
        double worldY = (viewSize.height / 2.0 - screenPoint.getY()) / zoom + panY;
        return new Point2D.Double(worldX, worldY);
    }

    public void zoom(double factor, Point zoomCenterScreen) {
        Point2D worldBefore = screenToWorld(zoomCenterScreen);
        zoom *= factor;
        // Clamp zoom to reasonable range
        zoom = Math.max(0.05, Math.min(10, zoom));
        Point2D worldAfter = screenToWorld(zoomCenterScreen);

        panX -= worldAfter.getX() - worldBefore.getX();
        panY -= worldAfter.getY() - worldBefore.getY();
    }

    public double getZoom() {
        return zoom;
    }

    // Get visible world bounds
    public Rectangle getVisibleWorldBounds() {
        Point2D topLeft = screenToWorld(new Point(0, 0));
        Point2D bottomRight = screenToWorld(new Point(viewSize.width, viewSize.height));

        return new Rectangle(
                (int)Math.min(topLeft.getX(), bottomRight.getX()),
                (int)Math.min(topLeft.getY(), bottomRight.getY()),
                (int)Math.abs(bottomRight.getX() - topLeft.getX()),
                (int)Math.abs(bottomRight.getY() - topLeft.getY())
        );
    }

    public Dimension getWorldSize() {
        return WORLD_SIZE;
    }
}
