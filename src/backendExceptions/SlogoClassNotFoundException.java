package backendExceptions;

public class SlogoClassNotFoundException extends BackendException {
	private final static String COMMAND_NOT_FOUND = "Command not found: ";

	public SlogoClassNotFoundException(Exception ex, String commandName) {
		super(ex, createMessageString(commandName));
	}

	private static String createMessageString(String commandName) {
		return COMMAND_NOT_FOUND + commandName;
	}

}
