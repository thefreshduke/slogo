package backendExceptions;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
@SuppressWarnings("serial")
public class BackendException extends Exception {

    public BackendException (Exception ex, String messageToShow) {
        super(messageToShow, ex);
    }
}