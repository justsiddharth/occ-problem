package com.occ.code.util.impl;

import com.occ.code.exception.EmptyFileException;
import com.occ.code.exception.InvalidFilePathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FileUtil is a Helper class to read a file.
 *
 * read() - uses Java 8 Streams to read the file
 *          and returns List of read String from file.s
 */
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static List<String> read(String path) {
        log.info("The file path provided is : {}", path);
        List<String> result = new ArrayList<>();
        Path filePath = null;

        try {
            if ( path == null || path.length() == 0 ) {
                throw new IllegalArgumentException("Path cannot not be : " + path);
            }

            filePath = Paths.get(path);
            log.info("Starting to read the file.");

            if(!Files.exists(filePath)) {
                throw new FileNotFoundException("File not present on path : " + path);
            }

            Stream<String> lines = Files.lines(filePath);

            /*  Sanitizes the read lines, by filtering empty lines,
                trimming whitespaces in the file and collecting sanitized lines as a list. */
            result = lines.filter(line -> !line.isEmpty())
                    .map(line -> line.trim())
                    .collect(Collectors.toList());

            if(result.isEmpty()) {
                throw new EmptyFileException("The file is Empty. Please provide a valid file.");
            }
        } catch(IllegalArgumentException e) {
            log.error("Invalid file path. Path cannot not be empty or null.");
            throw new InvalidFilePathException("Invalid File path. Path : " + path);
        } catch(FileNotFoundException e) {
            log.error("Invalid file path. File doesn't exist on path : {}", path);
            throw new InvalidFilePathException("File not present on path : " + path);
        } catch(EmptyFileException e) {
                log.error("The file is empty. Path : {}", path);
                throw new EmptyFileException("The file is Empty.");
        } catch(IOException e) {
            log.error("IO Exception occured. {}", e.getMessage());
        }
        return result;
    }
}