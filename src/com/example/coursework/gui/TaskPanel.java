package com.example.coursework.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public abstract class TaskPanel extends JPanel
{
    protected static final int GAP = 8;

    protected final OutputArea output = new OutputArea();

    private final String title;
    private final JPanel header = new JPanel(new BorderLayout(GAP, GAP));

    protected TaskPanel(String title, String description)
    {
        super(new BorderLayout(GAP, GAP));
        this.title = title;
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        header.add(createDescription(description), BorderLayout.NORTH);
        add(header, BorderLayout.NORTH);
        add(createOutputPanel(), BorderLayout.CENTER);
    }

    public String getTitle()
    {
        return title;
    }

    protected void setInputPanel(JComponent inputs)
    {
        inputs.setBorder(BorderFactory.createTitledBorder("Входные данные"));
        header.add(inputs, BorderLayout.CENTER);
    }

    private JComponent createDescription(String description)
    {
        JTextArea text = new JTextArea(description);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setOpaque(false);
        text.setFont(UIManager.getFont("Label.font"));
        text.setBorder(BorderFactory.createTitledBorder("Задание"));
        return text;
    }

    private JComponent createOutputPanel()
    {
        JButton clear = new JButton("Очистить вывод");
        clear.addActionListener(e -> output.clear());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttons.add(clear);

        JPanel panel = new JPanel(new BorderLayout(GAP, GAP));
        panel.setBorder(BorderFactory.createTitledBorder("Результат"));
        panel.add(new JScrollPane(output), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }
}
