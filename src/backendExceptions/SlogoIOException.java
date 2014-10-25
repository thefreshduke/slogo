package backendExceptions;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
@SuppressWarnings("serial")
public class SlogoIOException extends BackendException {
    private static final String IO_EXCEPTION = "Input Ouput Exeception";

    public SlogoIOException (Exception ex, String cause) {
        super(ex, IO_EXCEPTION);
    }

    public SlogoIOException (Exception ex) {
        this(ex, "");
    }

}