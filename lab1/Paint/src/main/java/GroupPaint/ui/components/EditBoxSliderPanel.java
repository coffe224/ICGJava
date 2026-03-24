package GroupPaint.ui.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import javax.swing.event.EventListenerList;

public class EditBoxSliderPanel extends JPanel {

    private JSlider slider;
    private JTextField textField;
    private final EventListenerList listenerList = new EventListenerList();

    public EditBoxSliderPanel(int min, int max, int initialValue) {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 1. Initialize Text Field
        textField = new JTextField(String.valueOf(initialValue), 5);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new IntegerFilter());

        // Add listener to update slider when text changes (on Enter or Focus Lost)
        textField.addActionListener(e -> syncSliderFromText());
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                syncSliderFromText();
            }
        });

        // 2. Initialize Slider
        slider = new JSlider(min, max, initialValue);
        slider.setMajorTickSpacing((max - min) / 5); // Auto-calculate ticks
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);

        // 3. Synchronization Logic

        // Slider -> Text
        slider.addChangeListener(e -> {
            if (!textField.hasFocus()) {
                textField.setText(String.valueOf(slider.getValue()));
                fireStateChanged();
            }
        });

        // Add components to panel
        // Putting text field in North, Slider in Center creates a vertical stack
        add(textField, BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
    }

    private void syncSliderFromText() {
        try {
            String text = textField.getText().trim();
            if (text.isEmpty()) return;
            int value = Integer.parseInt(text);

            // Clamp value
            int min = slider.getMinimum();
            int max = slider.getMaximum();
            if (value < min) value = min;
            if (value > max) value = max;

            slider.setValue(value);
            textField.setText(String.valueOf(value));
            fireStateChanged();
        } catch (NumberFormatException ex) {
            // Revert to current slider value if invalid input
            textField.setText(String.valueOf(slider.getValue()));
        }
    }

    // --- Public API for External Use ---

    public int getValue() {
        return slider.getValue();
    }

    public void setValue(int value) {
        slider.setValue(value);
        textField.setText(String.valueOf(value));
    }

    public void setRange(int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);
        // Re-calculate ticks
        slider.setMajorTickSpacing((max - min) / 5);
    }

    public void addChangeListener(ChangeListener listener) {
        listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        listenerList.remove(ChangeListener.class, listener);
    }

    protected void fireStateChanged() {
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener listener : listenerList.getListeners(ChangeListener.class)) {
            listener.stateChanged(e);
        }
    }

    // --- Helper Class for Input Validation ---
    static class IntegerFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (isInteger(string)) super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {
            if (text == null) return;
            if (isInteger(text)) super.replace(fb, offset, length, text, attrs);
        }

        private boolean isInteger(String s) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
