package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static refactoredexceptionmanager.ErrorProcessor.incrementErrorCounter;


/**
 * Convenience methods for data parsing.
 */
public class ParseUtils {

    /**
     * @param exceptionName
     *     Takes string name of exception and converts
     *     it to the object of required class type for the
     *     subsequent operations.
     * @return object of <code>Exception</code> type for further calculations
     * @see ParseUtils#getExceptionClass(Object)
     */
    public Object convertExceptionStringNameToClassObject(String exceptionName) {
        Object newExceptionObject;
        Class<?> classToCreate;
        Constructor<?> constructor;

        try {
            //currently available only exceptions from java.lang package
            //TODO add algorithm for parsing name of exception to choose package name for dynamic creation of objects
            classToCreate = Class.forName("java.lang." + exceptionName);
        } catch (ClassNotFoundException classCastEx) {
            incrementErrorCounter();
            classToCreate = null;
            System.err.println(classCastEx.getMessage());
        }
        try {
            constructor = classToCreate.getConstructor(String.class);
        } catch (NoSuchMethodException noMethodEx) {
            constructor = null;
            incrementErrorCounter();
            System.err.println(noMethodEx.getMessage());
        }
        try {
            newExceptionObject = constructor.newInstance(exceptionName);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException reflectionEx) {
            newExceptionObject = null;
            incrementErrorCounter();
            System.err.println(reflectionEx.getMessage());
        }
        return newExceptionObject;
    }

    /**
     * @param object of type <code>Exception</code>
     * @return class name of the exception object
     */
    public Class getExceptionClass(Object object) {
        return object.getClass();
    }

    /**
     * @param o object that must be casted to exception class;
     * @param exceptionClass class to convert exception to;
     * @param <T> generic type for dynamic object conversion
     *           <br/>
     *           Since we can not cast object directly to <code>Exception</code> class
     *           we have to pass object of exception as a parameter and cast it manually.
     *           <br/>
     *           Please consider that this method is not acceptable for real development.
     * @return casted object of exception class
     */
    public <T extends Exception> T convertObjectToException(Object o, Class<T> exceptionClass) {
        try {
            return exceptionClass.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

}
