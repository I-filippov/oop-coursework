package com.example.lab1.strategy;

import com.example.lab1.model.Point;

public class HorseMovementStrategy implements MovementStrategy
{
    @Override
    public String getName()
    {
        return "Езда на лошади";
    }

    @Override
    public String move(Point from, Point to)
    {
        return "Герой едет на лошади из " + from + " в " + to;
    }
}
