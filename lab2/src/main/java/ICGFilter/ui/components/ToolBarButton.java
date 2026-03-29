package ICGFilter.ui.components;

import javax.swing.*;
import java.awt.*;

public class ToolBarButton extends JButton {
    public ToolBarButton(Icon icon, int iconSize) {
        super(icon);
        setPreferredSize(new Dimension(iconSize + 8, iconSize + 8));
        setMaximumSize(new Dimension(iconSize + 8, iconSize + 8));
    }
}
