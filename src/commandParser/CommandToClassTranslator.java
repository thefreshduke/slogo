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

public class CommandToClassTranslator {	
	private String myCommandToClassDelimeter = "=";
	public Map<String, String> translateCommandToClass(File file) throws IOException, BackendException {
		BufferedReader reader = null;
		// Maps command name to corresponding package class name (for instance, if commands
		// are in a different package.
		Map<String, String> commandToClassTranslation = new HashMap<String, String>(); 
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String spaceRemovedLine = line.replaceAll(" ", "");
				String[] splitLine = spaceRemovedLine.split(myCommandToClassDelimeter, 2);
				if (splitLine.length == 2) {
					// Error checking happens in the LanguageFile Parser that tries to 
					// map the command to a corresponding class name
					commandToClassTranslation.put(splitLine[0], splitLine[1]);
				}
			}
		} catch (FileNotFoundException e1) {
			throw new SlogoFileNotFoundException(e1, file.getName());

		} catch (IOException e2) {
			throw new SlogoIOException(e2);
		}
		reader.close();
		return commandToClassTranslation;
	}
}