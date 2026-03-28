package ICGFilter.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        getContentPane().setBackground(Color.GRAY);
    }
}
