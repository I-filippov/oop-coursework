package com.example.lab04.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTasks
{
    public static final String PREFIX = "_new_";


     //Среднее значение списка целых чисел

    public double average(List<Integer> numbers)
    {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }


     // Приводит строки к верхнему регистру и добавляет префикс

    public List<String> addPrefix(List<String> strings)
    {
        return strings.stream()
                .map(s -> PREFIX + s.toUpperCase())
                .collect(Collectors.toList());
    }


     //Квадраты элементов, которые встречаются в списке только один раз

    public List<Integer> uniqueSquares(List<Integer> numbers)
    {
        return numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey() * e.getKey())
                .collect(Collectors.toList());
    }


      //Последний элемент коллекции

    public <T> T last(Collection<T> collection)
    {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("коллекция пуста"));
    }


     //Сумма чётных чисел массива

    public int sumEven(int[] numbers)
    {
        return java.util.Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }


     //Преобразует строки в Map: первый символ - ключ, остальные - значение.

    public Map<Character, String> toMap(List<String> strings)
    {
        return strings.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (oldValue, newValue) -> newValue));
    }
}
