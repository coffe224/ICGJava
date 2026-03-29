package ICGFilter.ui;

import ICGFilter.core.FilterInfo;
import ICGFilter.ui.components.ToolBarButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class ToolBar extends JToolBar {
    private static final int ICON_SIZE = 24;

    public ToolBar(List<FilterInfo> filters) {

        ImageIcon openIcon = getResizedIcon("/icons/open.png");
        ToolBarButton openButton = new ToolBarButton(openIcon, ICON_SIZE);
        openButton.setToolTipText("Открыть");
        // open

        ImageIcon toggleViewIcon = getResizedIcon("/icons/toggle_view.png");
        ToolBarButton toggleViewButton = new ToolBarButton(toggleViewIcon, ICON_SIZE);
        toggleViewButton.setToolTipText("Сменить режим отображения");
        // change view


        add(openButton);
        addSeparator();
        add(toggleViewButton);
        addSeparator();

        for (FilterInfo filter : filters) {
            ImageIcon filterIcon = getResizedIcon(filter.iconPath());
            ToolBarButton filterButton = new ToolBarButton(filterIcon, ICON_SIZE);
            filterButton.setToolTipText(filter.description());
            // addActionListener for manager.applyFilter(filter.name());
            add(filterButton);
        }
    }


    private ImageIcon getResizedIcon(String iconPath) {
        // обработка нуля
        // обработка вообще каких угодно исключений

        System.out.println(iconPath);

        URL icon = getClass().getResource(iconPath);
        if (icon == null) {
            icon = getClass().getResource("/icons/default_icon.png");
            if (icon == null) {
                System.out.println("Default icon is null");
            }
        }

        ImageIcon originalIcon = new ImageIcon(icon);
        Image scaledImage = originalIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        return resizedIcon;
    }
}
