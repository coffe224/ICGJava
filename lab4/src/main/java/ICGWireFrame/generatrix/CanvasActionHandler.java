package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

public class CanvasActionHandler implements CanvasActionListener {
    private final ViewTransform viewTransform;
    private final EditorCanvas canvas;
    private final DrawingModel drawingModel;
    private Point lastMousePosition;
    private boolean isPanning = false;
    private Interactable hovered = null;

    public CanvasActionHandler(ViewTransform viewTransform, EditorCanvas canvas, DrawingModel drawingModel) {
        this.viewTransform = viewTransform;
        this.canvas = canvas;
        this.drawingModel = drawingModel;
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
        } else if (event.getID() == MouseEvent.MOUSE_MOVED) {
            handleMouseMoved(event);
        }
    }

    private void handleMouseMoved(MouseEvent e) {
        Interactable interactable = getHoveredInteractable(e.getPoint());
        if (interactable == hovered) {
            return;
        }

        if (hovered != null) {
            hovered.setHovered(false);
        }
        if (interactable != null) {
            interactable.setHovered(true);
        }
        hovered = interactable;
        System.out.println(interactable);
        canvas.repaint();
    }

    private Interactable getHoveredInteractable(Point screenPosition) {
        for (Interactable interactable : drawingModel.getInteractables()) {
            Rectangle hitbox = interactable.getHitbox(viewTransform);
            if (hitbox == null) {
                System.out.println("Skipped");
                continue;
            }
            if (hitbox.contains(screenPosition)) {
                return interactable;
            }
        }
        return null;
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

            if (hovered != null) {
                hovered.move(deltaX, deltaY);
            } else {
                viewTransform.pan(-deltaX, -deltaY);
            }

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