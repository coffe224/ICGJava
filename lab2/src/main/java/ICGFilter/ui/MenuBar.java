package ICGFilter.ui;

import ICGFilter.core.FilterWrapper;

import javax.swing.*;
import java.util.List;

public class MenuBar extends JMenuBar {
    public MenuBar(List<FilterWrapper> filters) {
        JMenu fileMenu = createFileMenu();
        JMenu filtersMenu = createFiltersMenu(filters);
        JMenu optionsMenu = createOptionsMenu();

        add(fileMenu);
        add(filtersMenu);
        add(optionsMenu);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem openItem = new JMenuItem("Открыть");
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        // open file

        JMenuItem saveItem = new JMenuItem("Сохранить");
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        // save file

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

    private JMenu createFiltersMenu(List<FilterWrapper> filters) {
        JMenu filtersMenu = new JMenu("Фильтры");

        for (FilterWrapper filter : filters) {
            JMenu filterItem = new JMenu(filter.getDescription());
            filtersMenu.add(filterItem);
        }

        return filtersMenu;
    }

    private JMenu createOptionsMenu() {
        JMenu optionsMenu = new JMenu("Дополнительно");

        JMenuItem optionsItem = new JMenuItem("Настройки");
        // настройки

        JMenuItem aboutItem = new JMenuItem("О программе");
        // о программе

        optionsMenu.add(optionsItem);
        optionsMenu.add(aboutItem);
        return optionsMenu;
    }
}

