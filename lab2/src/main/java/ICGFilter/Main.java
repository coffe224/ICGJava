package ICGFilter;

import ICGFilter.core.Filter;
import ICGFilter.core.FilterInfo;
import ICGFilter.core.FilterManager;
import ICGFilter.ui.MainFrame;
import ICGFilter.ui.MenuBar;
import ICGFilter.ui.ToolBar;
import ICGFilter.ui.ViewFrame;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        FilterManager filterManager = new FilterManager();
        Map<String, Filter> filters = filterManager.getFilters();
        List<FilterInfo> filtersInfo = filterManager.getFiltersInfo();

        System.out.println(filters.size());
        for (FilterInfo filterInfo : filtersInfo) {
            System.out.println(filterInfo.name());
            System.out.println(filterInfo.iconPath());
            System.out.println(filterInfo.description() + "\n");
        }

        MenuBar menuBar = new MenuBar(filtersInfo);
        ToolBar toolBar = new ToolBar(filtersInfo);
        ViewFrame viewFrame = new ViewFrame();

        MainFrame mainFrame = new MainFrame(menuBar, toolBar, viewFrame);

        SwingUtilities.invokeLater(() ->
                mainFrame.setVisible(true)
        );
    }
}