package util;

import lombok.Getter;
import refactoredexceptionmanager.IFileManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static refactoredexceptionmanager.ErrorProcessor.incrementErrorCounter;


/**
 * Convenience methods for working with file system.
 */
public class FileManager implements IFileManager {

    @Getter
    private ParseUtils parseUtils;

    public FileManager(ParseUtils parseUtils) {
        this.parseUtils = parseUtils;
    }

    @Override
    public List<Class> readExceptionListFile(String filename) {
        List<Class> exceptionList = new ArrayList<>();

        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource(filename)).toURI());
            try (Stream<String> exceptions = Files.lines(path)) {
                exceptions.forEach(e -> exceptionList.add(parseUtils.getExceptionClass(
                                                          parseUtils.convertExceptionStringNameToClassObject(e))));
            }

        } catch (URISyntaxException e) {
            incrementErrorCounter();
            System.err.println(e.getMessage());
        } catch (IOException e) {
            incrementErrorCounter();
            System.err.println("Could not read file. " + e.getMessage());
        }
        return exceptionList;
    }

}
