package ICGFilter.ui;

import ICGFilter.core.ApplicationListener;
import ICGFilter.core.ImageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewPanel extends JPanel implements PropertyChangeListener {
    private static final int DEFAULT_WIDTH = 720;
    private static final int DEFAULT_HEIGHT = 640;

    private ApplicationListener applicationListener;
    private ImageView image;

    public ViewPanel(ImageView image) {
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(Color.GRAY);

        this.image = image;
        image.setListener(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (applicationListener != null) {
                    applicationListener.toggleProcessedView();
                }
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (applicationListener != null) {
                    applicationListener.handleResize(getWidth(), getHeight());
                }
            }
        });
    }

    public void setListener(ApplicationListener listener) {
        this.applicationListener = listener;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage canvas = image.getImage();
        g.drawImage(canvas, 0, 0, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("image".equals(evt.getPropertyName())) {
            Dimension newSize = (Dimension) evt.getNewValue();
            setPreferredSize(newSize);
            System.out.println(newSize);
            revalidate();
            repaint();
        }
    }
}