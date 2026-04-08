package ICGFilter.ui;

import ICGFilter.core.ApplicationListener;
import ICGFilter.core.FilterInfo;
import ICGFilter.ui.dialogs.HelpDialog;

import javax.swing.*;
import java.util.List;

public class MenuBar extends JMenuBar {
    private ApplicationListener applicationListener;

    public MenuBar(List<FilterInfo> filters) {
        JMenu fileMenu = createFileMenu();
        JMenu filtersMenu = createFiltersMenu(filters);
        JMenu viewMenu = createViewMenu();
        JMenu optionsMenu = createOptionsMenu();

        add(fileMenu);
        add(filtersMenu);
        add(viewMenu);
        add(optionsMenu);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem openItem = new JMenuItem("Открыть");
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        openItem.addActionListener(
                e -> applicationListener.openImage()
        );

        JMenuItem saveItem = new JMenuItem("Сохранить");
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        saveItem.addActionListener(
                e -> applicationListener.saveImage()
        );

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        exitItem.addActionListener(
                e -> System.exit(0)
        );


        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu createFiltersMenu(List<FilterInfo> filters) {
        JMenu filtersMenu = new JMenu("Фильтры");

        for (FilterInfo filter : filters) {
            JMenuItem filterItem = new JMenuItem(filter.description());
            filtersMenu.add(filterItem);
        }

        return filtersMenu;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("Вид");

        ButtonGroup viewModeGroup = new ButtonGroup();
        JRadioButtonMenuItem realSizeView = new JRadioButtonMenuItem("Реальный размер");
        realSizeView.addActionListener(
                e -> applicationListener.setAdaptedView(false)
        );
        realSizeView.setSelected(true);

        JRadioButtonMenuItem adaptedView = new JRadioButtonMenuItem("Адаптированный размер");
        adaptedView.addActionListener(
                e -> applicationListener.setAdaptedView(true)
        );

        viewModeGroup.add(realSizeView);
        viewModeGroup.add(adaptedView);


        ButtonGroup imageModeGroup = new ButtonGroup();
        JRadioButtonMenuItem originalView = new JRadioButtonMenuItem("Оригинал");
        originalView.addActionListener(
                e -> applicationListener.setProcessedView(false)
        );

        JRadioButtonMenuItem processedView = new JRadioButtonMenuItem("Обработанное");
        processedView.addActionListener(
                e -> applicationListener.setProcessedView(true)
        );
        processedView.setSelected(true);

        imageModeGroup.add(originalView);
        imageModeGroup.add(processedView);

        viewMenu.add(realSizeView);
        viewMenu.add(adaptedView);
        viewMenu.addSeparator();
        viewMenu.add(originalView);
        viewMenu.add(processedView);

        return viewMenu;
    }

    private JMenu createOptionsMenu() {
        JMenu optionsMenu = new JMenu("Дополнительно");

        JMenuItem optionsItem = new JMenuItem("Настройки");
        // TODO: implement button

        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(
                e -> HelpDialog.showDialog(null)
        );

        optionsMenu.add(optionsItem);
        optionsMenu.add(aboutItem);
        return optionsMenu;
    }

    public void setListener(ApplicationListener listener) {
        this.applicationListener = listener;
    }
}

