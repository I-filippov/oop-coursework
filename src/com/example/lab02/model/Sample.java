package com.example.lab02.model;

import com.example.lab02.annotation.Repeat;
import java.util.List;
import java.util.function.Consumer;

public class Sample
{
    private final Consumer<String> output;

    public Sample(Consumer<String> output)
    {
        this.output = output;
    }

    @Repeat(2)
    public void hello(String name)
    {
        output.accept("Публичный метод hello, имя: " + name);
    }

    public int add(int first, int second)
    {
        return first + second;
    }

    @Repeat(3)
    protected void printText(String text)
    {
        output.accept("Защищенный метод, текст: " + text);
    }

    @Repeat(2)
    protected double multiply(int number, double factor)
    {
        return number * factor;
    }

    protected void skipProtected(String text)
    {
        output.accept("Это сообщение не должно появиться: " + text);
    }

    @Repeat(4)
    private void printNumbers(int first, int second, int third)
    {
        output.accept("Приватный метод, числа: " + first + ", " + second + ", " + third);
    }

    @Repeat(1)
    private void printList(String title, boolean flag, List<String> items)
    {
        output.accept("Приватный метод, список: " + title + ", флаг=" + flag
                + ", размер=" + items.size());
    }

    private void skipPrivate(long value)
    {
        output.accept("Это сообщение не должно появиться: " + value);
    }

    @Repeat(2)
    private void printPoint(Point point)
    {
        output.accept("Приватный метод, точка: " + point);
    }
}
