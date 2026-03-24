package GroupPaint.ui.components;

import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {
    public PaintFrame(CanvasPanel panel, MenuBar menuBar, ToolBar toolBar) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        Dimension min_dimension = new Dimension(640, 480);
        setMinimumSize(min_dimension);

        getContentPane().setBackground(Color.GRAY);

//        JPanel canvasContainer = new JPanel();
//        canvasContainer.add(panel, BorderLayout.CENTER);
//        canvasContainer.setBackground(Color.CYAN);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack();
    }
}
