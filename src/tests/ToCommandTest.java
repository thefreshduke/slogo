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
public class ToCommandTest {

    private static final double toTestAnswer = 7;

    @Test
    public void testToParsing () {
        setFactory();
        String command = "To cmd [ :x :y ] [ sum :x :y ]";
        String command2 = "To second [ :x :y ] [ difference :x :y ]";
        String use = "cmd 3 4";
        String use2 = "second 3 4";
        String use3 = "cmd second 3 4 3";
        try {
            String processedCommand = processInput(command);
            BaseCommand toCommand = CommandFactory.createCommand(processedCommand, false);
            toCommand.execute();
            String processedCommand2 = processInput(command2);
            BaseCommand toCommand2 = CommandFactory.createCommand(processedCommand2, false);
            toCommand2.execute();

            String convertedUse = processInput(use);
            BaseCommand useCommand = CommandFactory.createCommand(convertedUse, false);
            Double result = useCommand.execute();
            assertEquals(result, new Double(toTestAnswer));

            String convertedUse2 = processInput(use2);
            BaseCommand useCommand2 = CommandFactory.createCommand(convertedUse2, false);
            Double result2 = useCommand2.execute();
            assertEquals(result2, new Double(-1));

            String convertedUse3 = processInput(use3);
            BaseCommand useCommand3 = CommandFactory.createCommand(convertedUse3, false);
            Double result3 = useCommand3.execute();
            assertEquals(result3, new Double(2));
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

        LanguageFileParser myTranslator = new LanguageFileParser(new File(
                "src/resources/languages/English.properties"));
        CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory.setCommandToClassRelation(commandToClassTranslator
                    .translateCommandToClass(new File(
                            "src/resources/languages/EnglishToClassName.properties")));
        }
        catch (BackendException ex) {

        }
        return myTranslator.translateUserInputIntoEnglish(input);

    }
}
