package GroupPaint.ui.components;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CurrentColorButton extends JButton implements PropertyChangeListener {
    public CurrentColorButton() {
        setBackground(Color.BLACK);
        setEnabled(false);
        setText("   ");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("color")) {
            setBackground((Color)evt.getNewValue());
        }
    }
}
