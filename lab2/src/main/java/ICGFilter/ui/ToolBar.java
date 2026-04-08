package ICGFilter.ui;

import ICGFilter.core.ApplicationListener;
import ICGFilter.core.FilterInfo;
import ICGFilter.ui.components.ToolBarButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class ToolBar extends JToolBar {
    private static final int ICON_SIZE = 24;

    private ApplicationListener applicationListener;

    public ToolBar(List<FilterInfo> filters) {

        ImageIcon openIcon = getResizedIcon("/icons/open.png");
        ToolBarButton openButton = new ToolBarButton(openIcon, ICON_SIZE);
        openButton.setToolTipText("Открыть");
        openButton.addActionListener(
                e -> applicationListener.openImage()
        );

        ImageIcon toggleViewIcon = getResizedIcon("/icons/toggle_view.png");
        ToolBarButton toggleViewButton = new ToolBarButton(toggleViewIcon, ICON_SIZE);
        toggleViewButton.setToolTipText("Сменить режим отображения");
        toggleViewButton.addActionListener(
                e -> applicationListener.toggleAdaptedView()
        );

        add(openButton);
        addSeparator();
        add(toggleViewButton);
        addSeparator();

        for (FilterInfo filter : filters) {
            ImageIcon filterIcon = getResizedIcon(filter.iconPath());
            ToolBarButton filterButton = new ToolBarButton(filterIcon, ICON_SIZE);
            filterButton.setToolTipText(filter.description());
            filterButton.addActionListener(
                    e -> applicationListener.applyFilter(filter.name())
            );
            add(filterButton);
        }
    }

    public void setListener(ApplicationListener listener) {
        this.applicationListener = listener;
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
