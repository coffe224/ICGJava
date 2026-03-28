package GroupPaint.ui.dialogs;

import javax.swing.*;
import java.awt.*;

public class ColorSelector extends JDialog {

    private Color selectedColor;
    private JColorChooser colorChooser;
    private boolean confirmed = false;

    public ColorSelector(Frame owner, Color initialColor) {
        super(owner, "Select Color", true);

        initComponents(initialColor);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents(Color initialColor) {
        setLayout(new BorderLayout());

        colorChooser = new JColorChooser(initialColor != null ? initialColor : Color.WHITE);
        add(colorChooser, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            selectedColor = colorChooser.getColor();
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        getRootPane().setDefaultButton(okButton);

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 450);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}