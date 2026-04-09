package ICGWireFrame.generatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditorCanvas extends JPanel implements PropertyChangeListener {
    private CanvasActionListener listener = null;
    private final DrawingModel drawingModel;

    public EditorCanvas(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
        drawingModel.setListener(this);

        setBackground(Color.BLACK);
        setMouseListeners();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                if (listener != null) {
                    listener.handleResize(getWidth(), getHeight());
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawingModel.draw(g2d);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("drawable".equals(evt.getPropertyName())) {
            repaint();
        }
    }

    private void setMouseListeners() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                listener.handleAction(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                listener.handleAction(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                listener.handleAction(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                listener.handleAction(e);
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }

    public void setListener(CanvasActionListener listener) {
        this.listener = listener;
    }
}
