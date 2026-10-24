package com.example.lab1.model;

import com.example.lab1.strategy.MovementStrategy;

public class Hero
{
    private final String name;
    private Point position;
    private MovementStrategy strategy;

    public Hero(String name, Point position)
    {
        this.name = name;
        this.position = position;
    }

    public void setStrategy(MovementStrategy strategy)
    {
        this.strategy = strategy;
    }

    public MovementStrategy getStrategy()
    {
        return strategy;
    }

    public String move(Point destination)
    {
        if (strategy == null)
        {
            throw new IllegalStateException("Способ перемещения не установлен");
        }

        String report = strategy.move(position, destination);
        position = destination;
        return report;
    }

    public String getName()
    {
        return name;
    }

    public Point getPosition()
    {
        return position;
    }

    @Override
    public String toString()
    {
        return name + " находится в точке " + position;
    }
}
