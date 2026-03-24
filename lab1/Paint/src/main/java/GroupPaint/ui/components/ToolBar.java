package GroupPaint.ui.components;

import GroupPaint.PaintController;
import GroupPaint.ToolState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class ToolBar extends JToolBar {
    public ToolBar(PaintController controller, ToolState toolState) {

        JButton clearButton = new JButton("Очистка");
        clearButton.setToolTipText("Очистить область");
        clearButton.addActionListener(
                e -> controller.clearCanvas()
        );

        JButton lineButton = new JButton("Линия");
        lineButton.setToolTipText("Создать линию");
        lineButton.addActionListener(
                e -> controller.chooseLine()
        );

        JButton figureButton = new JButton("Штамп");
        figureButton.setToolTipText("Создать штамп");
        figureButton.addActionListener(
                e -> controller.chooseFigure()
        );


        JButton fillingButton = new JButton("Заливка");
        fillingButton.setToolTipText("Залить область");
        figureButton.addActionListener(
                e -> controller.chooseFilling()
        );


        CurrentColorButton currentColorButton = new CurrentColorButton();
        toolState.addListener(currentColorButton);

        ColorButton blueButton = new ColorButton(Color.BLUE, controller);
        blueButton.setToolTipText("Синий");

        ColorButton whiteButton = new ColorButton(Color.WHITE, controller);
        whiteButton.setToolTipText("Белый");

        ColorButton blackButton = new ColorButton(Color.BLACK, controller);
        blackButton.setToolTipText("Черный");

        ColorButton redButton = new ColorButton(Color.RED, controller);
        redButton.setToolTipText("Красный");

        JButton colorChooserButton = new JButton("Выбор цвета");
        colorChooserButton.setToolTipText("Выбор цвета");
        colorChooserButton.addActionListener(
                e -> controller.chooseColor()
        );


        add(clearButton);

        addSeparator();

        add(lineButton);
        add(figureButton);
        add(fillingButton);

        addSeparator();

        add(currentColorButton);

        addSeparator();

        add(whiteButton);
        add(redButton);
        add(blueButton);
        add(blackButton);

        add(colorChooserButton);


    }
}
