package commands.controlCommands;

import java.io.File;
import org.junit.Test;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;


public class ToCommandTest {

    @Test
    public void testToParsing(){
        String command = "To cmd [ :x :y ] [ sum :x :y ]";
        try {
            String processedCommand = processInput(command);
            CommandFactory.createCommand(processedCommand, false);
        }
        catch (BackendException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private String processInput (String input) throws BackendException {
        LanguageFileParser myTranslator =
                new LanguageFileParser(new File("src/resources/languages/English.properties"));
        CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory
                    .setCommandToClassRelation(commandToClassTranslator
                            .translateCommandToClass(new File(
                                                              "src/resources/languages/EnglishToClassName.properties")));
        }
        catch (BackendException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return myTranslator.translateUserInputIntoEnglish(input);

    }
}
