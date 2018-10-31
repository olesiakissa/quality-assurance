package exceptionmanager;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExceptionManager {

    private List<Class> criticalExceptionList;
    @Getter
    private int criticalExceptionCounter;
    @Getter
    private int regularExceptionCounter;

    public ExceptionManager() {
        criticalExceptionList = Arrays.asList(NumberFormatException.class,
                                              NullPointerException.class,
                                              FileNotFoundException.class,
                                              IOException.class);
    }

    public boolean isExceptionCritical(Exception exception) {
        return criticalExceptionList.contains(exception.getClass());
    }

    public void handleException(Exception exception) {
        if (criticalExceptionList.contains(exception.getClass())) {
            criticalExceptionCounter++;
        } else regularExceptionCounter++;
    }

}
