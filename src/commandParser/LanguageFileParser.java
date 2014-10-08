package commandParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;
import commands.BaseCommand;

public class LanguageFileParser {
	private static String myCommandSeparator = " ";
	private String myEquals = "=";
	private String myComma = ",";

	protected Map<String, Class<BaseCommand>> parseLanguageFile(String fileName) throws BackendException, IOException {
		BufferedReader reader = null;
		Map<String, Class<BaseCommand>> myCommandToClassMap = new HashMap<String, Class<BaseCommand>>();

		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			throw new SlogoFileNotFoundException(e1, fileName);
		}
		String line;
		try {
			while((line = reader.readLine()) != null) {
				String spaceRemovedLine = line.replaceAll(myCommandSeparator, "");
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
		reader.close();
		return myCommandToClassMap;
	}
}