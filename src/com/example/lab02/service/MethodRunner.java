package com.example.lab02.service;

import com.example.lab02.annotation.Repeat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MethodRunner
{
    private final ArgumentFactory factory;
    private final Consumer<String> output;

    public MethodRunner(ArgumentFactory factory, Consumer<String> output)
    {
        this.factory = factory;
        this.output = output;
    }

    public void describeAll(Object target)
    {
        output.accept("Методы класса " + target.getClass().getSimpleName() + ":");
        Arrays.stream(target.getClass().getDeclaredMethods())
                .sorted(Comparator.comparing(Method::getName))
                .forEach(this::describe);
    }

    public void runAll(Object target)
    {
        Arrays.stream(target.getClass().getDeclaredMethods())
                .filter(this::isNeeded)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> runMethod(target, method));
    }

    private void describe(Method method)
    {
        String modifiers = Modifier.toString(method.getModifiers());
        String parameters = Arrays.stream(method.getParameterTypes())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
        Repeat repeat = method.getAnnotation(Repeat.class);
        String annotation = repeat == null ? "без аннотации" : "@Repeat(" + repeat.value() + ")";
        String status = isNeeded(method) ? "будет вызван" : "не вызывается";
        output.accept("  " + modifiers + " " + method.getName() + "(" + parameters + ") — "
                + annotation + ", " + status);
    }

    private boolean isNeeded(Method method)
    {
        int modifiers = method.getModifiers();
        boolean hiddenMethod = Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers);
        return hiddenMethod && method.isAnnotationPresent(Repeat.class);
    }

    private void runMethod(Object target, Method method)
    {
        int times = method.getAnnotation(Repeat.class).value();
        method.setAccessible(true);
        output.accept("Вызываем метод " + method.getName() + ", количество вызовов: " + times);
        for (int i = 1; i <= times; i++)
        {
            callOnce(target, method);
        }
    }

    private void callOnce(Object target, Method method)
    {
        try
        {
            Object[] args = factory.createArgs(method.getParameterTypes());
            Object result = method.invoke(target, args);
            if (method.getReturnType() != void.class)
            {
                output.accept("Метод " + method.getName() + " вернул: " + result);
            }
        }
        catch (InvocationTargetException e)
        {
            output.accept("Метод " + method.getName() + " выбросил исключение: " + e.getCause());
        }
        catch (IllegalAccessException | IllegalArgumentException e)
        {
            output.accept("Метод " + method.getName() + " пропущен: " + e.getMessage());
        }
    }
}
