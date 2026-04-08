package ICGFilter;

import ICGFilter.core.*;
import ICGFilter.ui.MainFrame;
import ICGFilter.ui.MenuBar;
import ICGFilter.ui.ToolBar;
import ICGFilter.ui.ViewPanel;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // загрузка фильтров
        FilterLoader filterLoader = new FilterLoader();
        Map<String, Filter> filters = filterLoader.getFilters();
        List<FilterInfo> filtersInfo = filterLoader.getFiltersInfo();

        // работа с изображением
        ImageView imageView = new ImageView();
        ImageProcessor imageProcessor = new ImageProcessor();
        ImageScaler imageScaler = new ImageScaler();

        // фронтенд
        MenuBar menuBar = new MenuBar(filtersInfo);
        ToolBar toolBar = new ToolBar(filtersInfo);
        ViewPanel viewPanel = new ViewPanel(imageView);


        ImageManager imageManager = new ImageManager(imageProcessor, imageScaler, imageView, viewPanel);

        MainFrame mainFrame = new MainFrame(menuBar, toolBar, viewPanel);

        ApplicationController applicationController = new ApplicationController(filters, imageManager, mainFrame);


        SwingUtilities.invokeLater(() ->
                mainFrame.setVisible(true)
        );
    }
}