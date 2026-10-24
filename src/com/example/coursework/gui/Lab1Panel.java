package com.example.coursework.gui;

import com.example.lab1.model.Hero;
import com.example.lab1.model.Point;
import com.example.lab1.strategy.FlyMovementStrategy;
import com.example.lab1.strategy.HorseMovementStrategy;
import com.example.lab1.strategy.MovementStrategy;
import com.example.lab1.strategy.WalkMovementStrategy;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.util.List;

public class Lab1Panel extends TaskPanel
{
    private static final int COORDINATE_LIMIT = 1000;
    private static final String HERO_NAME = "Игрок";

    private final Hero hero = new Hero(HERO_NAME, new Point(0, 0));
    private final JSpinner xField = createCoordinateField();
    private final JSpinner yField = createCoordinateField();

    public Lab1Panel()
    {
        super("1. Паттерн «Стратегия»",
                "Герой (класс Hero) перемещается между двумя точками (метод move) различными "
                        + "способами: пешком, на лошади, по воздуху. Способ перемещения выбирается "
                        + "и меняется во время выполнения программы (паттерн «Стратегия»).");

        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(createStrategyRow(List.of(
                new WalkMovementStrategy(),
                new HorseMovementStrategy(),
                new FlyMovementStrategy())));
        inputs.add(createMoveRow());
        setInputPanel(inputs);
    }

    private JPanel createStrategyRow(List<MovementStrategy> strategies)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel("Способ перемещения:"));
        ButtonGroup group = new ButtonGroup();
        for (MovementStrategy strategy : strategies)
        {
            JRadioButton button = new JRadioButton(strategy.getName());
            button.addActionListener(e -> changeStrategy(strategy));
            group.add(button);
            row.add(button);
        }
        group.getElements().nextElement().setSelected(true);
        hero.setStrategy(strategies.get(0));
        return row;
    }

    private JPanel createMoveRow()
    {
        JButton move = new JButton("Переместить");
        move.addActionListener(e -> moveHero());
        JButton position = new JButton("Показать позицию");
        position.addActionListener(e -> output.println(hero.toString()));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel("Точка назначения: x"));
        row.add(xField);
        row.add(new JLabel("y"));
        row.add(yField);
        row.add(move);
        row.add(position);
        return row;
    }

    private void changeStrategy(MovementStrategy strategy)
    {
        hero.setStrategy(strategy);
        output.println("Способ перемещения: " + strategy.getName());
    }

    private void moveHero()
    {
        Point destination = new Point((Integer) xField.getValue(), (Integer) yField.getValue());
        output.println(hero.move(destination));
    }

    private static JSpinner createCoordinateField()
    {
        return new JSpinner(new SpinnerNumberModel(0, -COORDINATE_LIMIT, COORDINATE_LIMIT, 1));
    }
}
