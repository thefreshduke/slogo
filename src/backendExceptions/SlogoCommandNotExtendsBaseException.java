package backendExceptions;

@SuppressWarnings("serial")
public class SlogoCommandNotExtendsBaseException extends BackendException {
	private static final String COMMAND_NOT_EXTENDS_BASE = "Command does not extend BaseCommand Class: ";

	public SlogoCommandNotExtendsBaseException(Exception ex,
			String commandName) {
		super(ex, createMessageString(commandName));
	}
	
	public SlogoCommandNotExtendsBaseException(String commandName) {
		super(new Exception(""), commandName);
	}

	private static String createMessageString(String commandName){
		return COMMAND_NOT_EXTENDS_BASE + commandName;
	}
}
