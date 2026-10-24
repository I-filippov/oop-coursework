package com.example.lab04.input;

import com.example.lab04.service.StreamTasks;

import java.util.Arrays;

public enum StreamOperation
{
    AVERAGE("Среднее значение списка целых чисел", "Целые числа", "1, 2, 3, 4")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return String.valueOf(tasks.average(InputParser.integers(input)));
        }
    },
    ADD_PREFIX("Верхний регистр и префикс «" + StreamTasks.PREFIX + "»", "Строки", "abc, xyz")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return String.valueOf(tasks.addPrefix(InputParser.strings(input)));
        }
    },
    UNIQUE_SQUARES("Квадраты элементов, встречающихся один раз", "Целые числа", "1, 2, 2, 3, 4, 4")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return String.valueOf(tasks.uniqueSquares(InputParser.integers(input)));
        }
    },
    LAST("Последний элемент коллекции", "Элементы коллекции (можно оставить пустым)", "a, b, c")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return tasks.last(InputParser.strings(input));
        }
    },
    SUM_EVEN("Сумма чётных чисел массива", "Целые числа", "1111111, 24, 31, 40")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return String.valueOf(tasks.sumEven(InputParser.intArray(input)));
        }
    },
    TO_MAP("Преобразование строк в Map (первый символ — ключ)", "Строки", "дерево, дом, небо")
    {
        @Override
        public String execute(StreamTasks tasks, String input)
        {
            return String.valueOf(tasks.toMap(InputParser.strings(input)));
        }
    };

    private final String title;
    private final String inputHint;
    private final String example;

    StreamOperation(String title, String inputHint, String example)
    {
        this.title = title;
        this.inputHint = inputHint;
        this.example = example;
    }

    public abstract String execute(StreamTasks tasks, String input);

    public String getInputHint()
    {
        return inputHint + " через запятую или пробел";
    }

    public String getExample()
    {
        return example;
    }

    public String describeCall(String input)
    {
        return title + ": " + Arrays.toString(InputParser.strings(input).toArray());
    }

    @Override
    public String toString()
    {
        return title;
    }
}
