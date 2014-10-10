package commandParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;

public class LanguageFileParser {
    
        public LanguageFileParser(String fileName) throws BackendException{
            extractFromLanguageFile(fileName);
            SPECIAL_CHARACTERS.add("[");
            SPECIAL_CHARACTERS.add("]");
        }
        
        private static final Set<String> SPECIAL_CHARACTERS = new HashSet<>();
        private static final char COMMENT_INDICATOR = '#';
	private static String myCommandSeparator = " ";
	private String myEquals = "=";
	private String myComma = ",";
	private Map<String, String> myUserInputToEnglishTranslationMap;

	public Map<String, String> extractFromLanguageFile(String fileName) throws BackendException {
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
				if(spaceRemovedLine.charAt(0) == COMMENT_INDICATOR){
				    continue;
				}
				String[] splitLine = spaceRemovedLine.split(myEquals, 2);
				//TODO: check for invalid lines
				String commandName = splitLine[0].toLowerCase();
				String[] possibleInputs = splitLine[1].split(myComma);
				for(String possibleInput : possibleInputs){
					myUserInputToEnglishTranslationMap.put(possibleInput.toLowerCase(), commandName);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("IOException detected");
			//TODO: throw exception
		}
		
		return myUserInputToEnglishTranslationMap;
	}
	
	public String translateUserInputIntoEnglish(String userInput) {
		String translatedUserInput = "";
		//TODO: Change to string builder
		String[] userInputWords = userInput.split(myCommandSeparator);
		for (int i = 0; i < userInputWords.length; i++) {
		        String word = userInputWords[i].toLowerCase();
		        if(SPECIAL_CHARACTERS.contains(word) || isNumber(word)){
                            translatedUserInput += word + myCommandSeparator;
                            continue;
                        }
		        String translatedInputWord ="";
		        
			if (myUserInputToEnglishTranslationMap.containsKey(word)) {
				translatedInputWord = myUserInputToEnglishTranslationMap.get(word);
				translatedUserInput += translatedInputWord + myCommandSeparator;
			}
			else{
			    //TODO: throw
			}
		}
		return translatedUserInput.trim();
	}
	
	private boolean isNumber(String input){
	    try{
	        Double.parseDouble(input);
	        return true;
	    }
	    catch(NumberFormatException ex){
	        return false;
	    }
	}
}