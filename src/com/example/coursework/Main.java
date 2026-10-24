package com.example.coursework;

import com.example.coursework.gui.Lab1Panel;
import com.example.coursework.gui.Lab2Panel;
import com.example.coursework.gui.Lab3Panel;
import com.example.coursework.gui.Lab4Panel;
import com.example.coursework.gui.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            useSystemLookAndFeel();
            new MainFrame(List.of(
                    new Lab1Panel(),
                    new Lab2Panel(),
                    new Lab3Panel(),
                    new Lab4Panel())).setVisible(true);
        });
    }

    private static void useSystemLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ReflectiveOperationException | UnsupportedLookAndFeelException e)
        {
            // остаётся стандартное оформление Swing
        }
    }
}
