package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CanvasActionHandler implements CanvasActionListener {
    private final ViewTransform viewTransform;
    private final EditorCanvas canvas;
    private Point lastMousePosition;
    private boolean isPanning = false;

    public CanvasActionHandler(ViewTransform viewTransform, EditorCanvas canvas) {
        this.viewTransform = viewTransform;
        this.canvas = canvas;
        this.lastMousePosition = new Point(0, 0);
    }

    @Override
    public void handleAction(MouseEvent event) {
        System.out.println("Mouse action handling");
        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            handleMousePressed(event);
        } else if (event.getID() == MouseEvent.MOUSE_DRAGGED) {
            handleMouseDragged(event);
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            handleMouseReleased(event);
        } else if (event.getID() == MouseEvent.MOUSE_WHEEL) {
            handleMouseWheel(event);
        }
    }

    private void handleMousePressed(MouseEvent e) {
        lastMousePosition = e.getPoint();
        if (e.getButton() == MouseEvent.BUTTON1) {
            isPanning = true;
            canvas.setCursor(java.awt.Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (isPanning) {
            Point currentPoint = e.getPoint();
            double deltaX = (currentPoint.x - lastMousePosition.x) / viewTransform.getZoom();
            double deltaY = -(currentPoint.y - lastMousePosition.y) / viewTransform.getZoom();

            System.out.println(deltaX + " " + deltaY);
            viewTransform.pan(-deltaX, -deltaY);

            lastMousePosition = currentPoint;
            canvas.repaint();
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        isPanning = false;
        canvas.setCursor(java.awt.Cursor.getDefaultCursor());
    }

    private void handleMouseWheel(MouseEvent e) {
        MouseWheelEvent wheelEvent = (MouseWheelEvent) e;
        double rotation = wheelEvent.getPreciseWheelRotation();
        double zoomFactor = rotation > 0 ? 1.1 : 0.9;

        // Zoom at mouse position
        viewTransform.zoom(zoomFactor, wheelEvent.getPoint());
        canvas.repaint();
    }

    @Override
    public void handleResize(int newWidth, int newHeight) {
        viewTransform.setViewSize(new java.awt.Dimension(newWidth, newHeight));
        canvas.repaint();
    }
}