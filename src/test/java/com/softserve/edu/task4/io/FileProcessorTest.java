package com.softserve.edu.task4.io;

import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by cdc89 on 05.06.2017.
 */
public class FileProcessorTest {
    @Test
    public void GetContent_EmptyTestFile_EmptyList() throws IOException {
        //Arrange
        final int EXPECTED = 0;
        FileProcessor fileProcessor = new FileProcessor
                ("src/test/resources/testEmpty.txt");

        //Act
        List<String> list = fileProcessor.getContent().get();

        //Assert
        assertEquals(EXPECTED, list.size());
    }

    @Test
    public void GetContent_TestFile2String_List2Element() throws IOException {
        //Arrange
        final int EXPECTED = 2;
        FileProcessor fileProcessor = new FileProcessor
                ("src/test/resources/test2String.txt");

        //Act
        List<String> list = fileProcessor.getContent().get();

        //Assert
        assertEquals(EXPECTED, list.size());
    }

    @Test(expected = IOException.class)
    public void GetContent_TestFakeFile_ThrowIOException() throws IOException {
        //Arrange
        FileProcessor fileProcessor = new FileProcessor
                ("src/test/resources/fake.txt");

        //Act
        fileProcessor.getContent();
    }

    @Test
    public void CountString_ListWithCoincidence_Return3() throws
            IOException {
        //Arrange
        final int EXPECTED = 3;
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        List<String> list = new ArrayList<>();
        list.add("input first input string");
        list.add("second string input");
        Optional<List> optional = Optional.of(list);
        when(fileProcessor.getContent()).thenReturn(optional);

        //Act
        int result = fileProcessor.countStrings("input");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void CountString_ListWithNoCoincidence_ReturnZero() throws
            IOException {
        //Arrange
        final int EXPECTED = 0;
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        List<String> list = new ArrayList<>();
        list.add("input first input string");
        list.add("second string input");
        Optional<List> optional = Optional.of(list);
        when(fileProcessor.getContent()).thenReturn(optional);

        //Act
        int result = fileProcessor.countStrings("NoInput");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void CountString_EmptyList_ReturnZero() throws IOException {
        //Arrange
        final int EXPECTED = 0;
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        Optional<List> optional = Optional.of(new ArrayList<>());
        when(fileProcessor.getContent()).thenReturn(optional);

        //Act
        int result = fileProcessor.countStrings("input");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void CountString_NullList_ReturnZero() throws IOException {
        //Arrange
        final int EXPECTED = 0;
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fp = new FileProcessor(stubFileName);
        FileProcessor fileProcessor = spy(fp);
        Optional<List> optional = Optional.empty();
        when(fileProcessor.getContent()).thenReturn(optional);

        //Act
        int result = fileProcessor.countStrings("input");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void ReplaceString_ListWithCoincidence_ChangeContent() throws
            IOException {
        //Arrange
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        List<String> list = new ArrayList<>();
        list.add("input first input string");
        list.add("second string input");
        final List<String> EXPECTED = new ArrayList<>();
        EXPECTED.add("output1 first output1 string");
        EXPECTED.add("second string output1");
        Optional<List> optional = Optional.of(list);
        when(fileProcessor.getContent()).thenReturn(optional);
        doNothing().when(fileProcessor).writeToFile(anyList());

        //Act
        List<String> result = fileProcessor.replaceString("input", "output1");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void ReplaceString_ListWithNoCoincidence_NotChangeContent() throws
            IOException {
        //Arrange
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        List<String> EXPECTED = new ArrayList<>();
        EXPECTED.add("input first input string");
        EXPECTED.add("second string input");
        Optional<List> optional = Optional.of(EXPECTED);
        when(fileProcessor.getContent()).thenReturn(optional);
        doNothing().when(fileProcessor).writeToFile(anyList());

        //Act
        List<String> result = fileProcessor.replaceString("output1",
                "output2");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void ReplaceString_EmptyList_NotChangeContent() throws IOException {
        //Arrange
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        List<String> EXPECTED = new ArrayList<>();
        Optional<List> optional = Optional.of(EXPECTED);
        when(fileProcessor.getContent()).thenReturn(optional);
        doNothing().when(fileProcessor).writeToFile(anyList());

        //Act
        List<String> result = fileProcessor.replaceString("output1",
                "output2");

        //Assert
        assertEquals(EXPECTED, result);
    }

    @Test
    public void ReplaceString_NullList_NullContent() throws IOException {
        //Arrange
        final String stubFileName = "src/test/resources/testEmpty.txt";
        FileProcessor fileProcessor = spy(new FileProcessor(stubFileName));
        Optional<List> optional = Optional.empty();
        when(fileProcessor.getContent()).thenReturn(optional);
        doNothing().when(fileProcessor).writeToFile(anyList());

        //Act
        List<String> result = fileProcessor.replaceString("output1",
                "output2");

        //Assert
        assertEquals(null, result);
    }
}
