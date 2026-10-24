package com.example.lab1.strategy;

import com.example.lab1.model.Point;

public class FlyMovementStrategy implements MovementStrategy
{
    @Override
    public String getName()
    {
        return "Полет";
    }

    @Override
    public String move(Point from, Point to)
    {
        return "Герой летит из " + from + " в " + to;
    }
}
