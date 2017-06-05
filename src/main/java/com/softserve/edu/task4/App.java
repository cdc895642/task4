package com.softserve.edu.task4;

import com.softserve.edu.task4.io.FileProcessor;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Hello world!
 */
public class App {

    private static PrintStream out = System.out;
    private static FileProcessor fileProcessor;

    public static void main(String[] args) {
        final int FILENAME_INDEX = 0;
        final int TARGET_STRING_INDEX = 1;
        final int REPLACEMENT_STRING_INDEX = 2;
        final String RULES = "Ошибка при передаче аргументов в программу "
                + ":\nПрограмма должна "
                + "принимать аргументы на вход при запуске:\n"
                + "1.\t<путь к файлу> <строка для подсчёта>\n"
                + "2.\t<путь к файлу> <строка для поиска> <строка для "
                + "замены>\n";
        if (args.length < 2) {
            out.print(RULES);
            return;
        }
        try {
            if (fileProcessor == null) {
                fileProcessor = new FileProcessor(args[FILENAME_INDEX]);
            }
            if (args.length == 2) {
                int count = fileProcessor.countStrings
                        (args[TARGET_STRING_INDEX]);
                out.println(String.format("count of the string \"%s\" "
                                + "in the file - %s",
                        args[TARGET_STRING_INDEX], count));
            }
            if (args.length == 3) {
                fileProcessor.replaceString(args[TARGET_STRING_INDEX],
                        args[REPLACEMENT_STRING_INDEX]);
                out.println("process finished");
            }
        } catch (IOException e) {
            out.println("Some exceptions was thrown when fileProcessor"
                    + " read/write the file:");
            e.printStackTrace();
        }
    }

    public static void setOut(PrintStream out) {
        App.out = out;
    }

    public static void setFileProcessor(FileProcessor fileProcessor) {
        App.fileProcessor = fileProcessor;
    }
}
