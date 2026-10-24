package com.example.coursework.gui;

import com.example.lab02.model.Sample;
import com.example.lab02.service.ArgumentFactory;
import com.example.lab02.service.MethodRunner;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Lab2Panel extends TaskPanel
{
    private final MethodRunner runner = new MethodRunner(new ArgumentFactory(), output::println);
    private final Sample sample = new Sample(output::println);

    public Lab2Panel()
    {
        super("2. Аннотации и рефлексия",
                "Аннотация @Repeat с целочисленным параметром. Класс Sample содержит публичные, "
                        + "защищённые и приватные методы с параметрами. Все аннотированные "
                        + "защищённые и приватные методы вызываются через рефлексию столько раз, "
                        + "сколько указано в аннотации; аргументы создаются по типам параметров.");

        JButton describe = new JButton("Показать методы класса");
        describe.addActionListener(e -> runner.describeAll(sample));
        JButton run = new JButton("Вызвать аннотированные методы");
        run.addActionListener(e -> runAnnotated());

        JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputs.add(describe);
        inputs.add(run);
        setInputPanel(inputs);
    }

    private void runAnnotated()
    {
        output.println("--- Запуск аннотированных защищённых и приватных методов ---");
        runner.runAll(sample);
        output.println("--- Готово ---");
    }
}
