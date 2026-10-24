package com.example.coursework.gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

public class MainFrame extends JFrame
{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 680;
    private static final int LIST_WIDTH = 230;

    private final CardLayout cards = new CardLayout();
    private final JPanel content = new JPanel(cards);

    public MainFrame(List<TaskPanel> tasks)
    {
        super("Курсовая работа: лабораторные работы 1–4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] titles = tasks.stream().map(TaskPanel::getTitle).toArray(String[]::new);
        tasks.forEach(task -> content.add(task, task.getTitle()));

        JList<String> taskList = new JList<>(titles);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(e -> showTask(taskList.getSelectedValue()));
        taskList.setSelectedIndex(0);

        JScrollPane listScroll = new JScrollPane(taskList);
        listScroll.setBorder(BorderFactory.createTitledBorder("Выберите задание"));
        listScroll.setMinimumSize(new Dimension(LIST_WIDTH, 0));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroll, content);
        split.setDividerLocation(LIST_WIDTH);
        setContentPane(split);

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    private void showTask(String title)
    {
        if (title != null)
        {
            cards.show(content, title);
        }
    }
}
