package tests;

import static org.junit.Assert.assertEquals;
import java.io.File;
import org.junit.Test;
import turtle.Position;
import turtle.Turtle;
import View.Grid;
import View.SingleGrid;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.information.IInformationGateway;
import commands.information.SingleGridInformationGateway;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class TurtleCommandsTest {

    @Test
    public void testToParsing () {
        setFactory();
        String input = "FD SUM 50 50 SUM 25 30";
        String input2 = "BK DIFFERENCE 70 20";
        String input3 = "RT 90";

        try {
            String processedCommand = processInput(input);
            BaseCommand command = CommandFactory.createCommand(processedCommand, false);
            Double result = command.execute();
            assertEquals(new Double(55), result); 

            String processedCommand2 = processInput(input2);
            command = CommandFactory.createCommand(processedCommand2, false);
            result = command.execute();
            assertEquals(new Double(50), result); 

            String processedCommand3 = processInput(input3);
            command = CommandFactory.createCommand(processedCommand3, false);
            result = command.execute();
            assertEquals(new Double(90), result); 
        }
        catch (BackendException ex) {

        }
    }

    private void setFactory () {
        Grid grid = new SingleGrid();
        Turtle turtle = new Turtle(new Position(0, 0), null);
        IInformationGateway hub = new SingleGridInformationGateway(grid, turtle);
        CommandFactory.setInformationGateway(hub);
    }

    private String processInput (String input) throws BackendException {

        LanguageFileParser myTranslator =
                new LanguageFileParser(new File("src/resources/languages/English.properties"));
        CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory.setCommandToClassRelation(
                    commandToClassTranslator.translateCommandToClass(
                            new File("src/resources/languages/EnglishToClassName.properties")));
        }
        catch (BackendException ex) {

        }
        return myTranslator.translateUserInputIntoEnglish(input);

    }
}