package refactoredexceptionmanager.testdoubles;

import lombok.Getter;
import refactoredexceptionmanager.IFileManager;

import java.util.ArrayList;
import java.util.List;

import static refactoredexceptionmanager.ErrorProcessor.*;


/**
 * Stub implementation of File Manager for testing purposes.
 */
public class FileManagerStub implements IFileManager {

    @Getter
    private List<Class> exceptionList = new ArrayList<>();
    @Getter
    private List<Class> criticalExceptionList = new ArrayList<>();

    public FileManagerStub() {
        fillServerExceptionListWithCannedValues();
        fillCriticalExceptionListWithCannedValues();
    }

    /**
     * Imitates reading of server configuration file.
     */
    private void fillServerExceptionListWithCannedValues() {
        exceptionList.add(NullPointerException.class);
        exceptionList.add(ArrayStoreException.class);
        exceptionList.add(InterruptedException.class);
        exceptionList.add(NoSuchFieldException.class);
        exceptionList.add(SecurityException.class);
    }


    /**
     * Imitates reading of file with critical exceptions.
     */
    private void fillCriticalExceptionListWithCannedValues() {
        criticalExceptionList.add(NumberFormatException.class);
        criticalExceptionList.add(NullPointerException.class);
        criticalExceptionList.add(RuntimeException.class);
    }

    @Override
    public List<Class> readExceptionListFile(String filename) {
        switch (filename) {
            case "t_crex.txt":
                return criticalExceptionList;
            case "t_config.txt":
                return exceptionList;
            default:
                incrementErrorCounter();
                return null;
        }
    }
}
