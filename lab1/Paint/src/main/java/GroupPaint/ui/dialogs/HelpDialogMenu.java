package GroupPaint.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpDialogMenu extends JDialog {

    private JTextArea textArea;
    private JButton okButton;

    public HelpDialogMenu(JFrame parent) {
        super(parent, "О программе", true); // Modal dialog
        initialize();
    }

    private void initialize() {
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setText(getProgramInfo());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(okButton);

        add(mainPanel);

        getRootPane().setDefaultButton(okButton);
    }

    private String getProgramInfo() {
        return "ICGPaint\n" +
                "=============================================\n\n" +
                "Версия: 1.0.0\n" +
                "Сделано: студент ФИТ НГУ Черных Сергей 23202\n\n" +
                "пейнт\n";
    }
}