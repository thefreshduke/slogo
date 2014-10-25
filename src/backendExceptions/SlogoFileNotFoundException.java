package backendExceptions;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
@SuppressWarnings("serial")
public class SlogoFileNotFoundException extends BackendException {

    private static final String FILE_NOT_FOUND = "File not found: ";

    public SlogoFileNotFoundException (Exception ex, String fileName) {
        super(ex, createMessageString(fileName));
    }

    private static String createMessageString (String fileName) {
        return FILE_NOT_FOUND + fileName;
    }
}