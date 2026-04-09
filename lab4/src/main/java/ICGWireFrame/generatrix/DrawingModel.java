package ICGWireFrame.generatrix;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
    private final List<Drawable> drawables;
    private final ViewTransform viewTransform;
    private PropertyChangeListener listener = null;

    public DrawingModel(ViewTransform viewTransform) {
        drawables = new ArrayList<>();
        this.viewTransform = viewTransform;
    }

    public void draw(Graphics2D g2d) {
        for (Drawable drawable : drawables) {
            drawable.draw(g2d, viewTransform);
        }
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    public void clearDrawables() {
        drawables.clear();
    }

    private void fireChangeEvent() {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "drawable", null, null);
        listener.propertyChange(evt);
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
}
