package com.softserve.edu.task4;

import com.softserve.edu.task4.io.FileProcessor;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int FILENAME_INDEX=0;
        int TARGET_STRING_INDEX=1;
        int REPLACEMENT_STRING_INDEX=2;
        if (args.length < 2) {
            System.out.println("Ошибка при передаче аргументов в программу :\nПрограмма должна " +
                    "принимать аргументы на вход при запуске:\n" +
                    "1.\t<путь к файлу> <строка для подсчёта>\n" +
                    "2.\t<путь к файлу> <строка для поиска> <строка для замены>\n");
            return;
        }
        try {
            FileProcessor fileProcessor = new FileProcessor(args[FILENAME_INDEX]);
            if (args.length == 2) {
                int count = fileProcessor.countStrings(args[TARGET_STRING_INDEX]);
                System.out.println(String.format("count of the string \"%s\" in the file - %s",
                        args[TARGET_STRING_INDEX], count));
            }
            if (args.length == 3){
                fileProcessor.replaceString(args[TARGET_STRING_INDEX],args[REPLACEMENT_STRING_INDEX]);
                System.out.println("process finished");
            }
        } catch (IOException e) {
            System.out.println("Some exceptions was thrown when fileProcessor read/write the " +
                    "file:");
            e.printStackTrace();
        }
    }
}
