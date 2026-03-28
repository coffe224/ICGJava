package GroupPaint.ui.components;

import GroupPaint.CanvasState;
import GroupPaint.PaintController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CanvasPanel extends JPanel implements PropertyChangeListener {
    private final PaintController controller;
    private final CanvasState canvasState;

    public CanvasPanel(PaintController controller, CanvasState canvasState, int width, int height) {
        this.controller = controller;
        this.canvasState = canvasState;

        canvasState.addListener(this);

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setBackground(Color.GREEN);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getPoint());
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.handleResize(getWidth(), getHeight());
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage canvas = canvasState.getCanvas();
        g.drawImage(canvas, 0, 0, null);
    }


    private void handleMouseClick(Point point) {
        controller.addPoint(point);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("canvas".equals(evt.getPropertyName())) {
            repaint();
        }

        if ("resize".equals(evt.getPropertyName())) {
            Dimension newSize = (Dimension) evt.getNewValue();
            Dimension preferredSize = new Dimension(newSize.width, newSize.height);
            // подумать нужны ли old size и old value (не нужны)
            setPreferredSize(preferredSize);
        }
    }
}
