package commandParser;

import java.util.Map;
import commands.BaseCommand;
import commands.NumericalCommand;


public class CommandFactory {

    private static Map<String, Class> myCommandToClassMap;
    private static String myCommandSeparator = "\\s+";

    // TODO: Need to figure out how to call the parseLanguageFile method in LanguageFileParser class
    // only once. This populates the myCommandToClassMap object.

    public static BaseCommand createCommand (String input, boolean isExpression) {
        String firstCommand = identifyFirstCommand(input);
        String subInput = input.replaceFirst(firstCommand, "").trim();
        if(checkIfInt(firstCommand)){
            int integerValue = Integer.parseInt(firstCommand);
            return new NumericalCommand(subInput, integerValue);
        }
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        if (commandClass == null) {
            // TODO: throw something
        }
        BaseCommand command = null;
        try {
            command = (BaseCommand) commandClass.getConstructor(String.class, boolean.class).newInstance(subInput, isExpression);
        }
        catch (Exception ex) {
        }
        return command;
    }

    private static boolean checkIfInt(String string){
        try{
            Integer.parseInt(string);
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
