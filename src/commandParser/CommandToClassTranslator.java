package commandParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import commands.BaseCommand;
import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;
import backendExceptions.SlogoIOException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 * 
 */
public class CommandToClassTranslator {

    private static final String CLASS_NOT_FOUND = "Class Not Found";
    private String myCommandToClassDelimeter = "=";
    private static final int MY_NUM_SPLIT_PARAMS = 2;

    public Map<String, Class> translateCommandToClass(File file)
            throws BackendException {
        BufferedReader reader = null;
        // Maps command name to corresponding package class name (for instance,
        // if commands
        // are in a different package.
        Map<String, Class> commandToClassTranslation = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String spaceRemovedLine = line.replaceAll("\\s+", "");
                String[] splitLine = spaceRemovedLine.split(
                        myCommandToClassDelimeter, MY_NUM_SPLIT_PARAMS);
                if (splitLine.length == MY_NUM_SPLIT_PARAMS) {
                    String englishCommandName = splitLine[0].toLowerCase();
                    String className = splitLine[1];
                    Class commandClass = Class.forName(className);
                    if (BaseCommand.class.isAssignableFrom(commandClass)) {
                        commandToClassTranslation.put(englishCommandName,
                                commandClass);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e1) {
            throw new SlogoFileNotFoundException(e1, file.getName());
        } catch (IOException e2) {
            throw new SlogoIOException(e2);
        } catch (NullPointerException e3) {
            throw new NullPointerException();
        } catch (ClassNotFoundException e) {
            throw new BackendException(e, CLASS_NOT_FOUND);
        }

        return commandToClassTranslation;
    }
}