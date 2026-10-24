package com.example.lab1.strategy;

import com.example.lab1.model.Point;

public class WalkMovementStrategy implements MovementStrategy
{
    @Override
    public String getName()
    {
        return "Ходьба";
    }

    @Override
    public String move(Point from, Point to)
    {
        return "Герой идет пешком из " + from + " в " + to;
    }
}
