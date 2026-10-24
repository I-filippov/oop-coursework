package com.example.coursework.gui;

import com.lab.translator.exception.FileReadException;
import com.lab.translator.exception.InvalidFileFormatException;
import com.lab.translator.model.Dictionary;
import com.lab.translator.repository.DictionaryReader;
import com.lab.translator.repository.FileDictionaryReader;
import com.lab.translator.repository.FileTextReader;
import com.lab.translator.service.Translator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.nio.file.Path;

public class Lab3Panel extends TaskPanel
{
    private static final String DEFAULT_DICTIONARY_FILE = "dictionary.txt";
    private static final String DEFAULT_TEXT_FILE = "text.txt";
    private static final int PATH_COLUMNS = 40;
    private static final int INPUT_ROWS = 5;

    private final DictionaryReader dictionaryReader = new FileDictionaryReader();
    private final FileTextReader textReader = new FileTextReader();

    private final JTextField dictionaryField = createPathField(DEFAULT_DICTIONARY_FILE);
    private final JTextField textFileField = createPathField(DEFAULT_TEXT_FILE);
    private final JLabel dictionaryStatus = new JLabel("Словарь не загружен");
    private final JTextArea inputText = new JTextArea(INPUT_ROWS, 0);

    private Translator translator;

    public Lab3Panel()
    {
        super("3. Переводчик",
                "Словарь читается из файла формата «слово или выражение | перевод». Регистр "
                        + "игнорируется, слова без перевода выводятся как есть, при нескольких "
                        + "вариантах выбирается вариант с наибольшей левой частью. Ошибки "
                        + "обрабатываются исключениями InvalidFileFormatException и FileReadException.");

        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(createFileRow("Файл словаря:", dictionaryField, "Загрузить словарь",
                this::loadDictionary));
        inputs.add(createStatusRow());
        inputs.add(createFileRow("Файл с текстом:", textFileField, "Загрузить текст",
                this::loadText));
        inputs.add(createTextPanel());
        setInputPanel(inputs);
    }

    private JPanel createFileRow(String label, JTextField field, String loadTitle, Runnable load)
    {
        JButton browse = new JButton("Обзор…");
        browse.addActionListener(e -> chooseFile(field, load));
        JButton loadButton = new JButton(loadTitle);
        loadButton.addActionListener(e -> load.run());

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(field);
        row.add(browse);
        row.add(loadButton);
        return row;
    }

    private JPanel createStatusRow()
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(dictionaryStatus);
        return row;
    }

    private JPanel createTextPanel()
    {
        inputText.setLineWrap(true);
        inputText.setWrapStyleWord(true);

        JButton translate = new JButton("Перевести");
        translate.addActionListener(e -> translate());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttons.add(translate);

        JPanel panel = new JPanel(new BorderLayout(GAP, GAP));
        panel.setBorder(BorderFactory.createTitledBorder(
                "Текст для перевода (загрузите из файла или введите вручную)"));
        panel.add(new JScrollPane(inputText), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }

    private void chooseFile(JTextField field, Runnable load)
    {
        JFileChooser chooser = new JFileChooser(new File(field.getText()).getAbsoluteFile());
        chooser.setSelectedFile(new File(field.getText()).getAbsoluteFile());
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            field.setText(chooser.getSelectedFile().getPath());
            load.run();
        }
    }

    private void loadDictionary()
    {
        String fileName = dictionaryField.getText().trim();
        try
        {
            Dictionary dictionary = dictionaryReader.read(fileName);
            translator = new Translator(dictionary);
            dictionaryStatus.setText("Словарь загружен: " + fileName + " (записей: "
                    + dictionary.size() + ")");
            output.println("Словарь загружен из файла " + fileName + ", записей: "
                    + dictionary.size());
        }
        catch (FileReadException | InvalidFileFormatException e)
        {
            translator = null;
            dictionaryStatus.setText("Словарь не загружен");
            output.printError(e.getMessage());
        }
    }

    private void loadText()
    {
        String fileName = textFileField.getText().trim();
        try
        {
            inputText.setText(textReader.read(fileName));
            output.println("Текст загружен из файла " + fileName);
        }
        catch (FileReadException e)
        {
            output.printError(e.getMessage());
        }
    }

    private void translate()
    {
        if (translator == null)
        {
            loadDictionary();
        }
        if (translator == null)
        {
            return;
        }
        String text = inputText.getText();
        if (text.isBlank())
        {
            output.printError("введите текст для перевода");
            return;
        }
        output.println("Исходный текст: " + text);
        output.println("Перевод: " + translator.translate(text));
    }

    private static JTextField createPathField(String fileName)
    {
        File file = Path.of(fileName).toAbsolutePath().toFile();
        return new JTextField(file.exists() ? file.getPath() : fileName, PATH_COLUMNS);
    }
}
