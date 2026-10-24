package com.example.coursework.gui;

import com.example.lab04.input.StreamOperation;
import com.example.lab04.service.StreamTasks;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.util.NoSuchElementException;

public class Lab4Panel extends TaskPanel
{
    private static final int INPUT_COLUMNS = 40;

    private final StreamTasks tasks = new StreamTasks();
    private final JComboBox<StreamOperation> operations = new JComboBox<>(StreamOperation.values());
    private final JLabel hint = new JLabel();
    private final JTextField inputField = new JTextField(INPUT_COLUMNS);

    public Lab4Panel()
    {
        super("4. Stream API",
                "Методы, реализованные только с помощью Stream API: среднее значение списка, "
                        + "верхний регистр с префиксом «_new_», квадраты уникальных элементов, "
                        + "последний элемент коллекции (или исключение), сумма чётных чисел, "
                        + "преобразование строк в Map.");

        operations.addActionListener(e -> showSelectedOperation());
        inputField.addActionListener(e -> execute());
        JButton run = new JButton("Выполнить");
        run.addActionListener(e -> execute());

        JPanel methodRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        methodRow.add(new JLabel("Метод:"));
        methodRow.add(operations);

        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputRow.add(new JLabel("Данные:"));
        inputRow.add(inputField);
        inputRow.add(run);

        JPanel hintRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hintRow.add(hint);

        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(methodRow);
        inputs.add(inputRow);
        inputs.add(hintRow);
        setInputPanel(inputs);

        showSelectedOperation();
    }

    private StreamOperation selectedOperation()
    {
        return (StreamOperation) operations.getSelectedItem();
    }

    private void showSelectedOperation()
    {
        StreamOperation operation = selectedOperation();
        hint.setText("Формат: " + operation.getInputHint());
        inputField.setText(operation.getExample());
    }

    private void execute()
    {
        StreamOperation operation = selectedOperation();
        String input = inputField.getText();
        output.println(operation.describeCall(input));
        try
        {
            output.println("  результат: " + operation.execute(tasks, input));
        }
        catch (IllegalArgumentException | NoSuchElementException e)
        {
            output.printError(e.getMessage());
        }
    }
}
