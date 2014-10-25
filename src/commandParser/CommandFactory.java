package commandParser;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.ICommandInformationHub;
import commands.information.IInformationContainer;

public class CommandFactory {

	private static Map<String, Class> myCommandToClassMap;
	private static String myCommandSeparator = "\\s+";
	private static ICommandInformationHub myInformationHub;
	private static Class[] x =  { BaseVariableContainer.class };

	// TODO: Need to figure out how to call the parseLanguageFile method in LanguageFileParser class
	// only once. This populates the myCommandToClassMap object.

	public static BaseCommand createCommand (String input, boolean isExpression) {
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
		try {
			BaseCommand command = null;
			if (commandClass == null) {
				BaseVariableContainer variableContainer = (BaseVariableContainer) myInformationHub.getContainer(BaseVariableContainer.class);
				command = variableContainer.getCreatedCommand(firstCommand, subInput, isExpression);
			}
			else {
				command = commandClass.getConstructor(String.class, boolean.class).newInstance(subInput, isExpression);
			}
			Set<Class<? extends IInformationContainer>> containerTypes = command.getRequiredInformationTypes();
			if (containerTypes != null) {
				Collection<IInformationContainer> containers = myInformationHub.getContainers(containerTypes);
				command.setRequiredInformation(containers);
			}
			return command;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static boolean checkIfNumerical(String string) {
		try {
			Double.parseDouble(string);
			return true;
		}
		catch (NumberFormatException ex) {
			return false;
		}
	}

	public static void setInformationHub(ICommandInformationHub informationHub) {
		myInformationHub = informationHub;
	}

	public static void setCommandToClassRelation (Map<String, Class> commandToClassMap) {
		myCommandToClassMap = commandToClassMap;
	}

	private void handleError (Exception ex) {

	}

	private static String identifyFirstCommand (String input) {
		String[] commandList = input.split(myCommandSeparator);
		String firstCommand = commandList[0];
		return firstCommand;
	}
}
