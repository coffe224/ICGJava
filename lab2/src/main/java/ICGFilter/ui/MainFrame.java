package ICGFilter.ui;

import ICGFilter.core.ApplicationListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private ViewPanel viewPanel;

    public MainFrame(MenuBar menuBar, ToolBar toolBar, ViewPanel viewPanel) {
        this.viewPanel = viewPanel;
        this.toolBar = toolBar;
        this.menuBar = menuBar;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        JScrollPane scrollPane = new JScrollPane(viewPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
    }

    public void setListener(ApplicationListener listener) {
        viewPanel.setListener(listener);
        menuBar.setListener(listener);
        toolBar.setListener(listener);
    }
}
