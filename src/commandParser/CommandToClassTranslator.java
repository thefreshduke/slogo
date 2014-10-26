package commandParser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 * 
 */
public class CommandToClassTranslator {

    private static final String INVALID_PROPERTIES_FILE = "Language file is not a valid .properties file";
	private static final String CLASS_NOT_FOUND = "Class Not Found";

    public Map<String, Class> translateCommandToClass(File file)
            throws BackendException {
        Map<String, Class> commandToClassTranslation = new HashMap<>();
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = getBundle(file);
        }
        catch (MalformedURLException e) {
            throw new BackendException(e, INVALID_PROPERTIES_FILE);
        }
        Enumeration<String> values = resourceBundle.getKeys();
        try{
        	while (values.hasMoreElements()) {
            	String key = values.nextElement().trim();
            	String value = resourceBundle.getString(key).trim();
            	Class commandClass = Class.forName(value);
            	commandToClassTranslation.put(key.toLowerCase(),  commandClass);
            }
        }
        catch (ClassNotFoundException e) {
			throw new BackendException(e, CLASS_NOT_FOUND);
		}
        return commandToClassTranslation;
    }
    
    private ResourceBundle getBundle(File file) throws MalformedURLException {
        File directory = file.getParentFile();
        URL[] urls = {directory.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        String fileName = getFileNameWithoutExtension(file);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);
        return rb;
    }

    private String getFileNameWithoutExtension(File file) {
        String fullFileName = file.getName();
        int pos = fullFileName.lastIndexOf(".");
        String fileNameWithoutExtension = "";
        if (pos > 0) {
            fileNameWithoutExtension = fullFileName.substring(0, pos);
        }
        return fileNameWithoutExtension;
    }
}