package refactoredexceptionmanager;


import java.util.List;

/**
 * Common interface for working with file system.
 */
public interface IFileManager {

    /**
     * @param filename file to read
     *                 <br/>
     *                 Reads the file which contains list of exceptions.
     *                 Dynamically converts each fetched string into exception of
     *                 required class type and adds it to the list of available exceptions.
     *                 <br/>
     * @return list of exceptions
     */
    List<Class> readExceptionListFile(String filename);

}
