package commandParser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class LanguageFileParser extends PropertiesFileReader{

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command provided";

    public LanguageFileParser (File fileName) throws BackendException {
        extractFromLanguageFile(fileName);
    }

    private static final String COMMAND_INDICATOR = "[a-zA-Z_]+(\\?)?";
    private static final char COMMENT_INDICATOR = '#';
    private static String COMMAND_SEPARATOR = "\\s+";
    private static String SINGLE_SPACE_SEPARATOR  = " ";
    private Map<String, String> myUserInputToEnglishTranslationMap;
    private Map<String, String> myPreviousTranslationMap;
    
    public Map<String, String> extractFromLanguageFile (File file) throws BackendException {
        myUserInputToEnglishTranslationMap = new HashMap<>();
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = getBundle(file);
        }
        catch (MalformedURLException e) {
            throw new BackendException(e, "Language file is not a valid .properties file");
        }
        Enumeration<String> values = resourceBundle.getKeys();
        while (values.hasMoreElements()) {
            String value = values.nextElement();
            String keys = resourceBundle.getString(value).trim();
            String[] keyList = keys.split("\\,");
            if (keyList.length <= 0) {
                setToPreviousMap();
                throw new BackendException(null, "Invalid language properties file");
            }
            for (String key : keyList) {
                myUserInputToEnglishTranslationMap.put(key.toLowerCase().trim(), value.toLowerCase().trim());
            }
        }
        myPreviousTranslationMap = myUserInputToEnglishTranslationMap;
        return myUserInputToEnglishTranslationMap;
    }

    private void setToPreviousMap() {
        myUserInputToEnglishTranslationMap = myPreviousTranslationMap;
    }

    public String translateUserInputIntoEnglish (String userInput) throws BackendException{
        StringBuilder translatedUserInput = new StringBuilder();
        String[] userInputWords = userInput.split(COMMAND_SEPARATOR);
        for (String rawCommand : userInputWords) {
            String command = rawCommand.toLowerCase().trim();
            String translatedCommand = translateCommand(command);
            if (translatedCommand == null || translatedCommand.equals("")) {
                translatedCommand = rawCommand;
            }
            translatedUserInput.append(translatedCommand);
            translatedUserInput.append(SINGLE_SPACE_SEPARATOR);
        }
        return translatedUserInput.toString().trim();
    }

    public String translateCommand(String command) throws BackendException {
        String translatedCommand;
        if (myUserInputToEnglishTranslationMap.containsKey(command)) {
            translatedCommand = myUserInputToEnglishTranslationMap.get(command);
        }
        else{
            translatedCommand = translateByRegex(command);
        }
        if(translatedCommand == null){
        	if(!command.matches(COMMAND_INDICATOR)){
        		throw new BackendException(null, INVALID_COMMAND_MESSAGE);
        	}
            translatedCommand = command;
        }
        return translatedCommand;
    }

    private String translateByRegex(String rawCommand) {
        Set<String> keySet = myUserInputToEnglishTranslationMap.keySet();
        for (String key : keySet) {
            try {
                if (rawCommand.matches(key)) {
                    String command = myUserInputToEnglishTranslationMap.get(key).toLowerCase();
                    if(command.equals("Variable".toLowerCase())) {
                        return command + " " + rawCommand.substring(1);
                    }
                    else {
                        return command + " " + rawCommand;
                    }
                }
            }
            catch(Exception ex) {
                continue;
            }
        }
        return null;
    }
}