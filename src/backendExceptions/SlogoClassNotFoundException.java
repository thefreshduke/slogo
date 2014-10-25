package backendExceptions;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
@SuppressWarnings("serial")
public class SlogoClassNotFoundException extends BackendException {
    private static final String COMMAND_NOT_FOUND = "Command not found: ";

    public SlogoClassNotFoundException (Exception ex, String commandName) {
        super(ex, createMessageString(commandName));
    }

    private static String createMessageString (String commandName) {
        return COMMAND_NOT_FOUND + commandName;
    }
}