package commands;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestFactory {
    
    private static Map<String, Class> myCommandToClassMap;
    private static String myCommandSeparator = " ";
    
    public static BaseCommand createCommand(String input, boolean isExpression){
        myCommandToClassMap = new HashMap<>();
        myCommandToClassMap.put("SUM", TestSumCommand.class);
        myCommandToClassMap.put("IFELSE", IfElseCommand.class);
        myCommandToClassMap.put("FD", ForwardCommand.class);
        myCommandToClassMap.put("BK", BackCommand.class);

        String firstCommand = identifyFirstCommand(input);
        String subInput = input.replaceFirst(firstCommand, "").trim();
        if(checkIfInt(firstCommand)){
            int integerValue = Integer.parseInt(firstCommand);
            return new NumericalCommand(subInput, integerValue);
        }
        else{
            Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
            try {
                return commandClass.getConstructor(String.class, boolean.class).newInstance(subInput, isExpression);
            }
            catch(Exception ex){
                return null;
            }
        }
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
    
    private static String identifyFirstCommand(String input){
        String[] commandList = input.trim().split(myCommandSeparator);
        String firstCommand = commandList[0];
        return firstCommand;
    }
}
