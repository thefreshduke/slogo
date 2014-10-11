package commandParser;

import java.util.Map;

import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.NumericalCommand;
import commands.variableCommands.GetVariableCommand;
import commands.variableCommands.SetVariableCommand;

public class CommandFactory {

    private static Map<String, Class> myCommandToClassMap;
    private static String myCommandSeparator = "\\s+";

    // TODO: Need to figure out how to call the parseLanguageFile method in LanguageFileParser class
    // only once. This populates the myCommandToClassMap object.

    public static BaseCommand createCommand (String input, boolean isExpression) {
    	String trimmedInput = input.trim();
    	if(input.charAt(0) == ':'){
    		try {
				return new GetVariableCommand(trimmedInput.substring(1), isExpression);
			} catch (BackendException e) {
				return null;
				//TODO: 
			}
    	}
        String firstCommand = identifyFirstCommand(input);
        String subInput = input.replaceFirst(firstCommand, "").trim();
        if(checkIfNumerical(firstCommand)){
            double integerValue = Double.parseDouble(firstCommand);
            try{
                return new NumericalCommand(subInput, integerValue);
            }
            catch (BackendException ex){
               //TODO:  
            }
        }
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        if (commandClass == null) {
            // TODO: throw something
        }
        BaseCommand command = null;
        try {
            command = commandClass.getConstructor(String.class, boolean.class).newInstance(subInput, isExpression);
        }
        catch (Exception ex) {
        }
        return command;
    }
    
    private static boolean checkIfNumerical(String string){
        try{
            Double.parseDouble(string);
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }
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
