package GroupPaint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CanvasState {
    private BufferedImage canvas;
    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public CanvasState(int canvasWidth, int canvasHeight) {
        canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        clearCanvas();
    }

    public BufferedImage getCanvas() {
        return canvas;
    }

    public void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.dispose();
        fireCanvasChangeEvent();
    }

    public void resizeCanvas(int newWidth, int newHeight) {
        Dimension oldSize = new Dimension(canvas.getWidth(), canvas.getHeight());
        BufferedImage newCanvas = new BufferedImage(newWidth, newHeight, canvas.getType());

        Graphics2D g2d = newCanvas.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, newWidth, newHeight);
        g2d.drawImage(canvas, 0, 0, null);
        g2d.dispose();

        this.canvas = newCanvas;
        fireCanvasResizeEvent(oldSize, new Dimension(newWidth, newHeight));
    }

    public void setCanvas(BufferedImage newCanvas) {

    }

    public void fireCanvasChangeEvent() {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(new PropertyChangeEvent(this, "canvas", null, null));
        }
    }

    public void fireCanvasResizeEvent(Dimension oldSize, Dimension newSize) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(new PropertyChangeEvent(this, "resize", oldSize, newSize));
        }
    }

    public void addListener(PropertyChangeListener l) { listeners.add(l); }
}
