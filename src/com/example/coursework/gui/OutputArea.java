package com.example.coursework.gui;

import javax.swing.JTextArea;
import java.awt.Font;

public class OutputArea extends JTextArea
{
    private static final int FONT_SIZE = 13;

    public OutputArea()
    {
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
    }

    public void println(String message)
    {
        append(message + System.lineSeparator());
        setCaretPosition(getDocument().getLength());
    }

    public void printError(String message)
    {
        println("Ошибка: " + message);
    }

    public void clear()
    {
        setText("");
    }
}
