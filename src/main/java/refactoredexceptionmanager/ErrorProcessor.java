package refactoredexceptionmanager;

import lombok.Getter;


/**
 * Class for processing server errors while handling exceptions.
 */
public class ErrorProcessor {

    @Getter
    private static int processingErrorCounter;

    /**
     * Increments error counter in case of errors.
     */
    public static void incrementErrorCounter() {
        processingErrorCounter++;
    }

}
