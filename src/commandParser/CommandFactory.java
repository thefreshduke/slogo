package commandParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import commands.BaseCommand;

public class CommandFactory {
    
    private static Map<String, Class<BaseCommand>> myCommandToClassMap;
    private static String myCommandSeparator;
    private String myEquals;
    private String myComma;
    
    protected void parseLanguageFile(String fileName) {
    	BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String line;
    	try {
			while((line = reader.readLine()) != null) {
				String spaceRemovedLine = line.replace("\\s+", "");
				String[] splitLine = spaceRemovedLine.split(myEquals, 2);
				String className = splitLine[0];
				String[] possibleInputs = splitLine[1].split(myComma);
				Class commandClass = Class.forName(className);
				boolean extendsBaseCommand = BaseCommand.class.isAssignableFrom(commandClass);
				if(extendsBaseCommand){
					for(String possibleInput : possibleInputs){
						myCommandToClassMap.put(possibleInput, commandClass);
					}
				}
				else{
					//TODO: Throw exception;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO: Make Exception 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static BaseCommand createCommand(String input){
        String firstCommand = identifyFirstCommand(input);
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        if(commandClass == null){
            //throw something
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
