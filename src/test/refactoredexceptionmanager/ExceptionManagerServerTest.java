package refactoredexceptionmanager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import refactoredexceptionmanager.testdoubles.FileManagerStub;
import util.ParseUtils;

import java.util.List;

import static org.mockito.Mockito.*;


public class ExceptionManagerServerTest {

    private ExceptionManagerServer managerServer;
    private IFileManager fileManager;
    private ParseUtils parseUtils;
    private List<Class> criticalExceptionsCannedValuesList;
    private List<Class> serverConfigExceptionCannedValuesList;
    private final String CRITICAL_EXCEPTIONS_TEST_FILENAME = "t_crex.txt";
    private final String SERVER_EXCEPTIONS_TEST_FILENAME = "t_config.txt";
    private ExceptionProcessor exceptionProcessor;

    @Before
    public void setUp() {
        fileManager = new FileManagerStub();
        parseUtils = Mockito.mock(ParseUtils.class);
        managerServer = Mockito.mock(ExceptionManagerServer.class);
        managerServer.setFileManager(fileManager);
        managerServer.setParseUtils(parseUtils);
        criticalExceptionsCannedValuesList = fileManager.readExceptionListFile(CRITICAL_EXCEPTIONS_TEST_FILENAME);
        serverConfigExceptionCannedValuesList = fileManager.readExceptionListFile(SERVER_EXCEPTIONS_TEST_FILENAME);
        managerServer.setCriticalExceptionsList(criticalExceptionsCannedValuesList);
        managerServer.setServerConfigExceptionList(serverConfigExceptionCannedValuesList);
        exceptionProcessor = new ExceptionProcessor(criticalExceptionsCannedValuesList);
        managerServer.setExceptionProcessor(exceptionProcessor);
    }

    @Test
    public void testProcessExceptions_VerifyExceptionProcessingCalledOnce() {
        managerServer.processExceptions(serverConfigExceptionCannedValuesList);
        verify(managerServer, times(1)).processExceptions(serverConfigExceptionCannedValuesList);
    }

    @Test
    public void testProcessExceptions_VerifyProcessingSuccess() {
        managerServer.processExceptions(serverConfigExceptionCannedValuesList);
        verify(managerServer).processExceptions(serverConfigExceptionCannedValuesList);
    }

    /**
     * Verifies that a particular method was not called on required list of exceptions.
     */
    @Test
    public void testProcessExceptions_VerifyProcessingFailure_InvalidArgument() {
        managerServer.processExceptions(criticalExceptionsCannedValuesList);
        verify(managerServer, never()).processExceptions(serverConfigExceptionCannedValuesList);
    }

    @Test
    public void testExceptionProcessor_HandleException_CalledAtLeastOnce() {
        ExceptionProcessor tempExceptionProcessor = Mockito.mock(ExceptionProcessor.class);
        for (int i = 0; i < serverConfigExceptionCannedValuesList.size(); i++) {
            tempExceptionProcessor.handleException(parseUtils.convertObjectToException
                    (parseUtils.convertExceptionStringNameToClassObject
                            (serverConfigExceptionCannedValuesList.get(i).getSimpleName()),
                            serverConfigExceptionCannedValuesList.get(i)));
        }
        //passing null argument for testing purposes
        verify(tempExceptionProcessor, atLeast(1)).handleException(null);
    }

    @Test
    public void testExceptionProcessor_HandleException_CalledForEachException() {
        ExceptionProcessor tempExceptionProcessor = Mockito.mock(ExceptionProcessor.class);
        for (int i = 0; i < serverConfigExceptionCannedValuesList.size(); i++) {
            tempExceptionProcessor.handleException(parseUtils.convertObjectToException
                    (parseUtils.convertExceptionStringNameToClassObject
                            (serverConfigExceptionCannedValuesList.get(i).getSimpleName()),
                            serverConfigExceptionCannedValuesList.get(i)));
        }
        verify(tempExceptionProcessor, atMost(serverConfigExceptionCannedValuesList.size())).handleException(null);
    }

}
