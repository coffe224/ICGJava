package ICGFilter.ui.components;

import javax.swing.*;
import java.awt.*;

public class ToolBarButton extends JButton {
    private static final int ICON_SIZE = 32;

    public ToolBarButton(Icon icon) {
        super(icon);
        setPreferredSize(new Dimension(ICON_SIZE + 8, ICON_SIZE + 8));
        setMaximumSize(new Dimension(ICON_SIZE + 8, ICON_SIZE + 8));
    }
}
