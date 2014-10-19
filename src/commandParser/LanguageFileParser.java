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
        String translatedUserInput = "";
        // TODO: Change to string builder
        String[] userInputWords = userInput.split(myCommandSeparator);
        for (int i = 0; i < userInputWords.length; i++) {
            String word = userInputWords[i].toLowerCase();
            if (!myUserInputToEnglishTranslationMap.containsKey(word) ||
                SPECIAL_CHARACTERS.contains(word) || isNumber(word)) {
                translatedUserInput += word + myCommandSeparator;
                continue;
            }
            String translatedInputWord = "";
            if (myUserInputToEnglishTranslationMap.containsKey(word)) {
                translatedInputWord = myUserInputToEnglishTranslationMap.get(word);
                translatedUserInput += translatedInputWord + myCommandSeparator;
            }
        }
        return translatedUserInput.trim();
    }

    private boolean isNumber (String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
