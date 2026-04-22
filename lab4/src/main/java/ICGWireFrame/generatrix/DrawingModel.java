package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
    private final List<Drawable> drawables;
    private final List<Interactable> interactables;
    private final ViewTransform viewTransform;
    private PropertyChangeListener listener = null;

    public DrawingModel(ViewTransform viewTransform) {
        drawables = new ArrayList<>();
        interactables = new ArrayList<>();
        this.viewTransform = viewTransform;
    }

    public void draw(Graphics2D g2d) {
        for (Drawable drawable : drawables) {
            drawable.draw(g2d, viewTransform);
        }
        for (Interactable interactable : interactables) {
            interactable.draw(g2d, viewTransform);
        }
    }

    public void addInteractable(Interactable interactable) {
        interactables.add(interactable);
    }

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        fireChangeEvent();
    }

    public void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
        fireChangeEvent();
    }

    public void clearDrawables() {
        drawables.clear();
        fireChangeEvent();
    }

    private void fireChangeEvent() {
        if (listener == null) {
            return;
        }
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "drawable", null, null);
        listener.propertyChange(evt);
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
}
