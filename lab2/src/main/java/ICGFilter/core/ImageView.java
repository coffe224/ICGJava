package ICGFilter.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ImageView {
    private BufferedImage image;
    PropertyChangeListener listener;

    public void setImage(BufferedImage image) {
        this.image = image;
        fireChangeEvent();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    private void fireChangeEvent() {
        Dimension newSize = new Dimension(image.getWidth(), image.getHeight());
        listener.propertyChange(new PropertyChangeEvent(this, "image", null, newSize));
    }
}
