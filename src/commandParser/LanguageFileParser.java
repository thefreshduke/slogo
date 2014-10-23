package commandParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;


public class LanguageFileParser {

    public LanguageFileParser (File fileName) throws BackendException {
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
        while(values.hasMoreElements()) {
            String value = values.nextElement();
            String keys = resourceBundle.getString(value).trim();
            String[] keyList = keys.split("\\,");
            if(keyList.length <= 0){
                setToPreviousMap();
                throw new BackendException(null, "Invalid language properties file");
            }
            for(String key : keyList){
                myUserInputToEnglishTranslationMap.put(key.toLowerCase().trim(), value.toLowerCase().trim());
            }
        }
        myPreviousTranslationMap = myUserInputToEnglishTranslationMap;
        return myUserInputToEnglishTranslationMap;
    }

    private void setToPreviousMap(){
        myUserInputToEnglishTranslationMap = myPreviousTranslationMap;
    }
    
    private ResourceBundle getBundle(File file) throws MalformedURLException{
        File directory = file.getParentFile();
        URL[] urls = {directory.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        String fileName = getFileNameWithoutExtension(file);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);
        return rb;
    }
    
    private String getFileNameWithoutExtension(File file){
        String fullFileName = file.getName();
        int pos = fullFileName.lastIndexOf(".");
        String fileNameWithoutExtension = "";
        if (pos > 0) {
            fileNameWithoutExtension = fullFileName.substring(0, pos);
        }
        return fileNameWithoutExtension;
    }
    
    public String translateUserInputIntoEnglish (String userInput) {
        StringBuilder translatedUserInput = new StringBuilder();
        // TODO: Change to string builder
        String[] userInputWords = userInput.split(myCommandSeparator);
        for(String rawCommand : userInputWords){
            String command = rawCommand.toLowerCase().trim();
            String translatedCommand = translateCommand(command);
            if(translatedCommand == null || translatedCommand.equals("")){
                translatedCommand = rawCommand;
            }
            translatedUserInput.append(translatedCommand);
            translatedUserInput.append(myCommandSeparator);
        }
        return translatedUserInput.toString().trim();
    }
    
    public String translateCommand(String command){
        String translatedCommand;
        if(myUserInputToEnglishTranslationMap.containsKey(command)){
            translatedCommand = myUserInputToEnglishTranslationMap.get(command);
        }
        else{
            translatedCommand = translateByRegex(command);
        }
        return translatedCommand;
    }
    
    private String translateByRegex(String rawCommand){
        Set<String> keySet = myUserInputToEnglishTranslationMap.keySet();
        for(String key : keySet){
        	try{
        		if(rawCommand.matches(key)){
                    String command = myUserInputToEnglishTranslationMap.get(key).toLowerCase();
                    if(command.equals("Variable".toLowerCase())){
                        return command + " " + rawCommand.substring(1);
                    }
                    else{
                        return command + " " + rawCommand;
                    }
                }
        	}
            
            catch(Exception ex){
            	continue;
            }
        }
        return null;
    }
}