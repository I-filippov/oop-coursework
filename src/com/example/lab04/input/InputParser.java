package com.example.lab04.input;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class InputParser
{
    private static final Pattern SEPARATOR = Pattern.compile("[,;\\s]+");

    private InputParser()
    {
    }

    public static List<String> strings(String input)
    {
        String trimmed = input.trim();
        if (trimmed.isEmpty())
        {
            return List.of();
        }
        return Arrays.stream(SEPARATOR.split(trimmed))
                .collect(Collectors.toList());
    }

    public static List<Integer> integers(String input)
    {
        return strings(input).stream()
                .map(InputParser::parseInt)
                .collect(Collectors.toList());
    }

    public static int[] intArray(String input)
    {
        return integers(input).stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static Integer parseInt(String value)
    {
        try
        {
            return Integer.valueOf(value);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("«" + value + "» не является целым числом");
        }
    }
}
