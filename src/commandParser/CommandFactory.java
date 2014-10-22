package commandParser;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.NumericalCommand;
import commands.information.BaseCommandInformationHub;
import commands.information.IInformationContainer;
import commands.variableCommands.GetVariableCommand;
import commands.variableCommands.MakeVariableCommand;

public class CommandFactory {

    private static Map<String, Class> myCommandToClassMap;
    private static String myCommandSeparator = "\\s+";

    // TODO: Need to figure out how to call the parseLanguageFile method in LanguageFileParser class
    // only once. This populates the myCommandToClassMap object.

    public static BaseCommand createCommand (String input, boolean isExpression) {
        if(input == null){
            return null;
        }
    	String trimmedInput = input.trim();
    	if(trimmedInput.equals("")){
    	    return null;
    	}
        String firstCommand = identifyFirstCommand(trimmedInput);
        String subInput = input.replaceFirst(firstCommand, "").trim();
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        if (commandClass == null) {
            // TODO: throw something
        }
        BaseCommand command = null;
        try {
            command = commandClass.getConstructor(String.class, boolean.class).newInstance(subInput, isExpression);
//            Set<Class<? extends IInformationContainer>> containerTypes = command.getRequiredInformationTypes();
//            BaseCommandInformationHub hub; 
//            Collection<IInformationContainer> containers = hub.getContainers(containerTypes);
//            command.setRequiredInfo(containers);
        }
        catch (Exception ex) {
        	ex.printStackTrace();
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
