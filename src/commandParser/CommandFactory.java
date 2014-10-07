package commandParser;

import java.util.Map;
import commands.BaseCommand;

public class CommandFactory {

	private static Map<String, Class<BaseCommand>> myCommandToClassMap;
	private static String myCommandSeparator;

	//TODO: Need to figure out how to call the parseLanguageFile method in LanguageFileParser class
	// only once.  This populates the myCommandToClassMap object.

	public static BaseCommand createCommand(String input){
		String firstCommand = identifyFirstCommand(input);
		Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
		if(commandClass == null){
			//TODO: throw something
		}
		String subInput = input.replaceFirst(firstCommand + myCommandSeparator, "");
		BaseCommand command = null;
		try {
			command = (BaseCommand) commandClass.getConstructor(String.class).newInstance(subInput);
		}
		catch(Exception ex){
			//TODO: Change to more specific exceptions;
		}
		//TODO: Throw exception
		return command;
	}

	//TODO: remove if unnecessary
	private void handleError(Exception ex){

	}

	private static String identifyFirstCommand(String input){
		String[] commandList = input.split(myCommandSeparator);
		String firstCommand = commandList[0];
		return firstCommand;
	}
}