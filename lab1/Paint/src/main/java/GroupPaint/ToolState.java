package GroupPaint;

import GroupPaint.tools.PaintTool;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ToolState {
    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private boolean isToolSelected = false;
    private PaintTool currentTool = null;
    private Color currentColor = Color.BLACK;

    public boolean isToolSelected() {
        return isToolSelected;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public PaintTool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentColor(Color color) {
        Color oldColor = currentColor;
        currentColor = color;
        fireColorChangeEvent(oldColor, currentColor);
    }

    public void setCurrentTool(PaintTool tool) {
        currentTool = tool;
        isToolSelected = true;
        fireToolChangeEvent(currentTool.getToolID());
    }

    public void unselectTool() {
        currentTool = null;
        isToolSelected = false;
    }

    public void addListener(PropertyChangeListener l) { listeners.add(l); }

    private void fireColorChangeEvent(Color oldColor, Color newColor) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(new PropertyChangeEvent(this, "color", null, newColor));
        }
    }

    private void fireToolChangeEvent(String newID) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(new PropertyChangeEvent(this, "tool", null, newID));
        }
    }
}
