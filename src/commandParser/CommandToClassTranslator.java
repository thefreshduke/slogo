package commandParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import backendExceptions.BackendException;
import backendExceptions.SlogoFileNotFoundException;
import backendExceptions.SlogoIOException;
import commands.BaseCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, Scotty Shaw
 *
 * Maps command name to corresponding package class name
 * For example, if commands are in a different package.
 *
 */
public class CommandToClassTranslator {

    private String myCommandToClassDelimeter = "=";

    @SuppressWarnings("rawtypes")
    public Map<String, Class> translateCommandToClass (File file) throws BackendException, ClassNotFoundException {
        BufferedReader reader = null;
        Map<String, Class> commandToClassTranslation = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String spaceRemovedLine = line.replaceAll("\\s+", "");
                String[] splitLine = spaceRemovedLine.split(myCommandToClassDelimeter, 2);
                if (splitLine.length == 2) {
                    String englishCommandName = splitLine[0].toLowerCase();
                    String className = splitLine[1];
                    Class commandClass = Class.forName(className);
                    if (BaseCommand.class.isAssignableFrom(commandClass)) {
                        commandToClassTranslation.put(englishCommandName, commandClass);
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e1) {
            throw new SlogoFileNotFoundException(e1, String.format("%s not found", file.getName()));
        }
        catch (IOException e2) {
            throw new SlogoIOException(e2, String.format("Input/Output Exception occurred"));
        }
        catch (NullPointerException e3) {
            throw new NullPointerException();
        }
        catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
        return commandToClassTranslation;
    }
}
