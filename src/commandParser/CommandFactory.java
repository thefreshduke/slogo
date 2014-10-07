package commandParser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import commands.BaseCommand;

public class CommandFactory {
    
    private static Map<String, Class<BaseCommand>> myCommandToClassMap;
    private static String myCommandSeparator;
    
    public static BaseCommand createCommand(String input){
        String firstCommand = identifyFirstCommand(input);
        String inputCopy = new String(input);
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        if(commandClass == null){
            //throw something
        }
        String subInput = inputCopy.replaceFirst(firstCommand, "");
        try {
            BaseCommand command = (BaseCommand) commandClass.getConstructor(String.class).newInstance(subInput);
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
        
    }
    
    private static String identifyFirstCommand(String input){
        String[] commandList = input.split(myCommandSeparator);
        String firstCommand = commandList[0];
        return firstCommand;
    }
}
