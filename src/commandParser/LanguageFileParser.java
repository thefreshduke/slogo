package commandParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;

public class LanguageFileParser {
	private static String myCommandSeparator = " ";
	private String myEquals = "=";
	private String myComma = ",";
	private Map<String, String> myUserInputToEnglishTranslationMap;

	protected Map<String, String> translateUserInputToEnglishTranslation(String fileName) throws BackendException, IOException, ClassNotFoundException {
		BufferedReader reader = null;
		myUserInputToEnglishTranslationMap = new HashMap<String, String>();
		//		CommandToClassTranslator myTranslator = new CommandToClassTranslator();
		//		myTranslator.translateCommandToClass();

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
				String commandName = splitLine[0];
				String[] possibleInputs = splitLine[1].split(myComma);
				for(String possibleInput : possibleInputs){
					myUserInputToEnglishTranslationMap.put(possibleInput, commandName);
				}
			}
		} catch (IOException e) {
			System.out.println("IOException detected");
			//TODO: throw exception
		}
		reader.close();
		return myUserInputToEnglishTranslationMap;
	}
	
	protected String translateUserInputIntoEnglish(String userInput) {
		String translatedUserInput = "";
		String[] userInputWords = userInput.split(myCommandSeparator);
		for (int i = 0; i < userInputWords.length; i++) {
			if (myUserInputToEnglishTranslationMap.containsKey(userInputWords[i])) {
				userInputWords[i] = myUserInputToEnglishTranslationMap.get(userInputWords[i]);
			}
			translatedUserInput = translatedUserInput + userInputWords[i] + myCommandSeparator;
		}
		return translatedUserInput.trim();
	}
}