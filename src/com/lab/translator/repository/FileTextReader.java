package com.lab.translator.repository;

import com.lab.translator.exception.FileReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileTextReader
{
    public String read(String fileName) throws FileReadException
    {
        Path path = toPath(fileName);
        if (!Files.isRegularFile(path))
        {
            throw new FileReadException("Файл не найден: " + fileName);
        }

        try
        {
            return Files.readString(path);
        }
        catch (IOException e)
        {
            throw new FileReadException("Не удалось прочитать файл: " + fileName);
        }
    }

    private Path toPath(String fileName) throws FileReadException
    {
        try
        {
            return Path.of(fileName);
        }
        catch (InvalidPathException e)
        {
            throw new FileReadException("Некорректный путь к файлу: " + fileName);
        }
    }
}
