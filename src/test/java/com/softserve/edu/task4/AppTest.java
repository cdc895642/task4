package com.softserve.edu.task4;


import com.softserve.edu.task4.io.FileProcessor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Unit test for App.
 */
public class AppTest {
    @Test
    public void Main_LessThan2Params_PrintRules() throws IOException {
        //Arrange
        String[] args = new String[]{"one"};
        final String RULES = "Ошибка при передаче аргументов в программу "
                + ":\nПрограмма должна " +
                "принимать аргументы на вход при запуске:\n" +
                "1.\t<путь к файлу> <строка для подсчёта>\n" +
                "2.\t<путь к файлу> <строка для поиска> <строка для замены>\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        App.setOut(new PrintStream(outContent));

        //Act
        App.main(args);

        //Assert
        assertEquals(RULES, outContent.toString());
        outContent.close();
    }

    @Test
    public void Main_2Params_PrintCountOfStrings() throws IOException {
        //Arrange
        String[] args = new String[]{"one", "two"};
        final String EXPECTED = "count of the string";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        App.setOut(new PrintStream(outContent));
        FileProcessor fileProcessor = mock(FileProcessor.class);
        App.setFileProcessor(fileProcessor);

        //Act
        App.main(args);

        //Assert
        assertTrue(outContent.toString().contains(EXPECTED));
        outContent.close();
        App.setFileProcessor(null);
    }

    @Test
    public void Main_3Params_ReplaceStrings() throws IOException {
        //Arrange
        String[] args = new String[]{"one", "two", "three"};
        final String EXPECTED = "process finished";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        App.setOut(new PrintStream(outContent));
        FileProcessor fileProcessor = mock(FileProcessor.class);
        App.setFileProcessor(fileProcessor);

        //Act
        App.main(args);

        //Assert
        assertTrue(outContent.toString().contains(EXPECTED));
        outContent.close();
        App.setFileProcessor(null);
    }

    @Test
    public void Main_WrongFileName_IOExceptionMessage() throws IOException {
        //Arrange
        String[] args = new String[]{"file", "two", "three"};
        final String EXPECTED = "Some exceptions was thrown when fileProcessor";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        App.setOut(new PrintStream(outContent));

        //Act
        App.main(args);

        //Assert
        assertTrue(outContent.toString().contains(EXPECTED));
        outContent.close();
    }
}
