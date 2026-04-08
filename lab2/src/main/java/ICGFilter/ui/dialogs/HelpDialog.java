package ICGFilter.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpDialog {

    public static void showDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "О программе", true); // Modal dialog
        initializeDialog(dialog);
        dialog.setVisible(true);
    }

    private static void initializeDialog(JDialog dialog) {
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setText(getProgramInfo());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(okButton);

        dialog.add(mainPanel);
        dialog.getRootPane().setDefaultButton(okButton);
    }

    private static String getProgramInfo() {
        return "ICGFilter\n" +
                "=============================================\n\n" +
                "Версия: 1.0.0\n" +
                "Сделано: студент ФИТ НГУ Черных Сергей 23202\n\n" +
                "фильтер\n";
    }
}