package GroupPaint.ui.dialogs.tooldialogs;

import GroupPaint.ui.components.EditBoxSliderPanel;

import javax.swing.*;
import java.awt.*;

public class FigureParamatersSelector extends JDialog {
    private EditBoxSliderPanel vertexPanel;
    private EditBoxSliderPanel anglePanel;
    private EditBoxSliderPanel radiusPanel;
    private boolean confirmed = false;

    public FigureParamatersSelector(Frame parent) {
        super(parent, "Параметры фигуры", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        vertexPanel = new EditBoxSliderPanel(3, 16, 3);
        anglePanel = new EditBoxSliderPanel(0, 180, 0);
        radiusPanel = new EditBoxSliderPanel(5, 200, 20);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(new JLabel("Выбрать число вершин:", SwingConstants.CENTER));
        topPanel.add(vertexPanel);
        topPanel.add(new JLabel("Выбрать угол:", SwingConstants.CENTER));
        topPanel.add(anglePanel);
        topPanel.add(new JLabel("Выбрать радиус:", SwingConstants.CENTER));
        topPanel.add(radiusPanel);

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

    public int getVertices() {
        return confirmed ? vertexPanel.getValue() : -1;
    }

    public int getAngle() {
        return confirmed ? anglePanel.getValue() : -1;
    }

    public int getRadius() {
        return confirmed ? radiusPanel.getValue() : -1;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
