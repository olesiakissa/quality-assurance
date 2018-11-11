package refactoredexceptionmanager;

import util.FileManager;
import util.ParseUtils;

import java.util.List;


public class App {

    private static ParseUtils parseUtils = new ParseUtils();
    private static IFileManager fileManager = new FileManager(parseUtils);
    private static final String CRITICAL_EXCEPTIONS_FILENAME = "crex_list.txt";
    private static final String SERVER_EXCEPTIONS_FILENAME = "config.txt";

    public static void main(String[] args) {
        ExceptionManagerServer managerServer = new ExceptionManagerServer(fileManager,
                                                                          CRITICAL_EXCEPTIONS_FILENAME,
                                                                          SERVER_EXCEPTIONS_FILENAME,
                                                                          parseUtils);
        List<Class> serverExceptions = managerServer.getServerConfigExceptionList();
        System.err.println(managerServer.processExceptions(serverExceptions));
    }

}
