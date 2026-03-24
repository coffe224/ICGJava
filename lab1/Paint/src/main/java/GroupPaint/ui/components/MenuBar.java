package GroupPaint.ui.components;

import GroupPaint.PaintController;
import GroupPaint.ToolState;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private PaintController controller;
    private ToolState toolState;

    public MenuBar(PaintController controller, ToolState toolState) {
        this.controller = controller;
        this.toolState = toolState;


        JMenu fileMenu = createFileMenu();
        JMenu editMenu = createEditMenu();
        JMenu helpMenu = createHelpMenu();

        add(fileMenu);
        add(editMenu);
        add(helpMenu);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setMnemonic('Ф'); // Горячая клавиша Alt+Ф

        JMenuItem openItem = new JMenuItem("Открыть");
        // контроллер опция открыть
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));

        JMenuItem saveItem = new JMenuItem("Сохранить");
        // контроллер опция сохранить
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        JMenuItem exitItem = new JMenuItem("Выход");
        // выйти
        exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Правка");
        editMenu.setMnemonic('П'); // Горячая клавиша Alt+П

        ButtonGroup toolGroup = new ButtonGroup();

        JRadioButtonMenuItem lineItem = new JRadioButtonMenuItem("Линия");
        lineItem.addActionListener(
                e -> controller.chooseLine()
        );


        JRadioButtonMenuItem figureItem = new JRadioButtonMenuItem("Фигура");
        JRadioButtonMenuItem fillingItem = new JRadioButtonMenuItem("Заливка");

        toolGroup.add(lineItem);
        toolGroup.add(figureItem);
        toolGroup.add(fillingItem);
        toolGroup.clearSelection();


        JMenuItem colorChoosingItem = new JMenuItem("Выбрать цвет");

        JMenuItem emptyCanvasItem = new JMenuItem("Очистить область");
        emptyCanvasItem.addActionListener(
                e -> controller.clearCanvas()
        );

        editMenu.add(lineItem);
        editMenu.add(figureItem);
        editMenu.add(fillingItem);
        editMenu.addSeparator();
        editMenu.add(colorChoosingItem);
        editMenu.addSeparator();
        editMenu.add(emptyCanvasItem);
        return editMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setMnemonic('С'); // Горячая клавиша Alt+С

        JMenuItem aboutItem = new JMenuItem("О программе");

        helpMenu.add(aboutItem);
        return helpMenu;
    }
}
