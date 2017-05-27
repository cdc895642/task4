package com.softserve.edu.task4.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cdc89 on 27.05.2017.
 * read and process file
 */
public class FileProcessor {
    private String fileName;

    public FileProcessor(String fileName) throws IOException {
        this.fileName = fileName;
        if (!Files.exists(Paths.get(fileName))) {
            throw new IOException(String.format("file %s does not exist", fileName));
        }
    }

    /**
     * return the number of occurrences of the input string in the file
     *
     * @param input input string
     * @return the number of occurrences of the input string in the file
     */
    public int countStrings(String input) throws IOException {
        List<String> content = getContent().orElse(new ArrayList<>());
        return (int) content.stream().
                flatMap(line -> Arrays.stream(line.split("\\s"))).
                filter(line -> line.contains(input)).count();
    }

    /**
     * Replaces each substring in the file that matches the target string with the
     * specified replacement string.
     *
     * @param target      string to be replaced
     * @param replacement replacement string
     */
    public void replaceString(String target, String replacement) throws IOException {
        List<String> content;
        if (getContent().isPresent()) {
            List<String> tempContent = getContent().get();
            content = tempContent.stream().map(line -> line.toString().replace(target,
                    replacement)).collect(Collectors.toList());
        } else {
            return;
        }
        Files.write(Paths.get(fileName), content);
    }

    private Optional<List> getContent() throws IOException {
        List<String> content;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            content = stream.collect(Collectors.toList());
        }
        return Optional.ofNullable(content);
    }
}
