package refactoredexceptionmanager;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * This class is used for exceptions processing and is also responsible
 * for error handling.
 */
public class ExceptionProcessor implements IExceptionManager {

    @Setter
    private List<Class> criticalExceptionList;
    @Getter
    private int criticalExceptionCounter;
    @Getter
    private int regularExceptionCounter;

    public ExceptionProcessor(List<Class> exceptionList) {
        this.criticalExceptionList = exceptionList;
    }

    @Override
    public boolean isExceptionCritical(Exception exception) {
        return criticalExceptionList.contains(exception.getClass());
    }

    @Override
    public boolean handleException(Exception exception) {
        boolean exceptionIsCritical = isExceptionCritical(exception);
        if (exceptionIsCritical) {
            System.out.printf("%s is critical. \n", exception.getClass().getSimpleName());
            criticalExceptionCounter++;
        } else {
            System.out.printf("%s is regular. \n", exception.getClass().getSimpleName());
            regularExceptionCounter++;
        }

        if (criticalExceptionCounter != 0 || regularExceptionCounter !=0 ) {
            return true;
        } else {
            ErrorProcessor.incrementErrorCounter();
            return false;
        }
    }


}
