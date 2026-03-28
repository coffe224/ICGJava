package GroupPaint.ui.dialogs.tooldialogs;

import GroupPaint.ui.components.EditBoxSliderPanel;

import javax.swing.*;
import java.awt.*;

public class LineParametersSelector extends JDialog {

    private EditBoxSliderPanel thicknessPanel;
    private boolean confirmed = false;

    public LineParametersSelector(Frame parent) {
        super(parent, "Line Parameters", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        thicknessPanel = new EditBoxSliderPanel(1, 50, 1);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Select Line Thickness:", SwingConstants.CENTER), BorderLayout.NORTH);
        topPanel.add(thicknessPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
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
    }

    public int getThickness() {
        return confirmed ? thicknessPanel.getValue() : -1;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}