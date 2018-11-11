package refactoredexceptionmanager;


/**
 * Common interface for processing information on server side.
 */
public interface IExceptionManager {

    /**
     * @param exception to check
     * @return
     *      <code>true</code> -- if exception is critical;
     *      <br/>
     *      <code>false</code> -- if exception is regular
     */
    boolean isExceptionCritical(Exception exception);

    /**
     * @param exception that server receives for processing
     *                  <br/>
     *                  Processes exception and sends a server response.
     * @return
     *      <code>true</code> -- if exception was handled without any errors;
     *      <br/>
     *      <code>false</code> -- if any errors were thrown on a server
     */
    boolean handleException(Exception exception);

}
