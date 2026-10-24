package com.example.lab1.strategy;

import com.example.lab1.model.Point;

public interface MovementStrategy
{
    String getName();

    String move(Point from, Point to);
}
