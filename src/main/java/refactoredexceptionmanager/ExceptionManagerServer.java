package refactoredexceptionmanager;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import util.ParseUtils;


/**
 * Class for processing exceptions that were thrown on server.
 */
public class ExceptionManagerServer {

    @Getter @Setter
    private List<Class> criticalExceptionsList;
    @Getter @Setter
    private List<Class> serverConfigExceptionList;
    @Getter @Setter
    private IFileManager fileManager;
    @Getter @Setter
    private ParseUtils parseUtils;
    @Getter @Setter
    private ExceptionProcessor exceptionProcessor;

    public ExceptionManagerServer(IFileManager fileManager,
                                  String criticalExceptionsFilename,
                                  String serverExceptionsFilename,
                                  ParseUtils parseUtils) {
        this.fileManager = fileManager;
        this.parseUtils = parseUtils;
        criticalExceptionsList = fileManager.readExceptionListFile(criticalExceptionsFilename);
        serverConfigExceptionList = fileManager.readExceptionListFile(serverExceptionsFilename);
        exceptionProcessor = new ExceptionProcessor(criticalExceptionsList);
    }

    /**
     * @param serverExceptionList file with exceptions that resides on server side
     *                            Processes the list of exceptions -- converts each item
     *                            in serverExceptionList to new Exception object.
     * @return string status code with the result of processing:
     * <br/>
     * <code>200</code> -- if there are no server errors;
     * <br/>
     * <code>506</code> -- if there are errors while processing or exceptions were thrown
     */
    public String processExceptions(List<Class> serverExceptionList) {
        StringBuilder result = new StringBuilder();
        try {
            for (int i = 0; i < serverExceptionList.size(); i++) {
                        exceptionProcessor.handleException(parseUtils.convertObjectToException
                        (parseUtils.convertExceptionStringNameToClassObject
                        (serverExceptionList.get(i).getSimpleName()), serverExceptionList.get(i)));
            }
        } finally {
            int errorCounter = ErrorProcessor.getProcessingErrorCounter();
            if (errorCounter == 0) {
                result.append("ERROR CODE: 200 OK");
            } else {
                result.append("ERROR CODE: 506 THE HANDLE IS INVALID. FOUND ").append(errorCounter).append(" ERRORS.");
            }
        }
        return result.toString();
    }

}
