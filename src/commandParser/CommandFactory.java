package commandParser;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.ICommandInformationHub;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 *         The Command Factory uses reflection to hide implementation details
 *         and creates the commands from the user's inputs using a key-value
 *         system.
 * 
 *         It is static because our createCommand method is recursive, so we
 *         need to avoid passing the factory to the individual commands.
 */
public class CommandFactory {

	private static final String INVALID_COMMAND_CLASS_TYPE_MESSAGE = "Invalid command class type";
	private static Map<String, Class> myCommandToClassMap;
	private static String myCommandSeparator = "\\s+";
	private static ICommandInformationHub myInformationHub;
	private static Class[] x = { BaseVariableContainer.class };

	public static BaseCommand createCommand(String input, boolean isExpression)
			throws BackendException {
		if (input == null || input.equals("")) {
			return null;
		}
		String trimmedInput = input.trim();
		if (trimmedInput.equals("")) {
			return null;
		}
		String firstCommand = identifyFirstCommand(trimmedInput);
		String subInput = input.replaceFirst(firstCommand, "").trim();
		Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
		BaseCommand command = null;
		if (commandClass == null) {
			BaseVariableContainer variableContainer = (BaseVariableContainer) myInformationHub
					.getContainer(BaseVariableContainer.class);
			if (variableContainer.containsCustomCommand(firstCommand)) {
				command = variableContainer.getCreatedCommand(firstCommand,
						subInput, isExpression);
			}
		} else {
			try {
				command = commandClass.getConstructor(String.class,
						boolean.class).newInstance(subInput, isExpression);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				throw new BackendException(e,
						INVALID_COMMAND_CLASS_TYPE_MESSAGE);
			}
		}
		if (command == null) {
			throw new BackendException(null, INVALID_COMMAND_CLASS_TYPE_MESSAGE);
		}
		Set<Class<? extends IInformationContainer>> containerTypes = command
				.getRequiredInformationTypes();
		if (containerTypes != null) {
			Collection<IInformationContainer> containers = myInformationHub
					.getContainers(containerTypes);
			command.setRequiredInformation(containers);
		}
		return command;
	}

	private static boolean checkIfNumerical(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static void setInformationHub(ICommandInformationHub informationHub) {
		myInformationHub = informationHub;
	}

	public static void setCommandToClassRelation(
			Map<String, Class> commandToClassMap) {
		myCommandToClassMap = commandToClassMap;
	}

	private void handleError(Exception ex) {

	}

	private static String identifyFirstCommand(String input) {
		String[] commandList = input.split(myCommandSeparator);
		String firstCommand = commandList[0];
		return firstCommand;
	}
}