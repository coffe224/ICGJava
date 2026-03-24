package GroupPaint.ui.components;

import GroupPaint.PaintController;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {
    public ColorButton(Color color, PaintController controller) {
        setBackground(color);
        setText("   ");
        addActionListener(
                e -> controller.chooseColor(color)
        );
    }
}
