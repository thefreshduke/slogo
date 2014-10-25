package commands.controlCommands;

import static org.junit.Assert.*;

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
import commands.information.ICommandInformationHub;
import commands.information.SingleViewContainerInformationHub;

public class ContainerTest {

    @Test
    public void testThatIfWorks () {
        String testString = "IF sum 1 0 [ fd 50 bk 75 ]";
        String translated = "";
        try {
            translated = processInput(testString);
        }
        catch (BackendException ex) {

        }
        BaseCommand command = CommandFactory.createCommand(translated, false);
        Double result = null;
        try {
            result = new Double(command.execute());
        }
        catch (BackendException ex) {

        }
        assertEquals(result, new Double(75.0));
    }

    private String processInput (String input) throws BackendException {
        Grid grid = new SingleGrid();
        Turtle turtle = new Turtle(new Position(0, 0), null);
        ICommandInformationHub hub = new SingleViewContainerInformationHub(grid, turtle);
        LanguageFileParser myTranslator = new LanguageFileParser(
                new File("src/resources/languages/English.properties"));
        CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory.setInformationHub(hub);
            CommandFactory
            .setCommandToClassRelation(commandToClassTranslator.translateCommandToClass(
                    new File("src/resources/languages/EnglishToClassName.properties")));
        }
        catch (BackendException ex) {

        }
        return   myTranslator.translateUserInputIntoEnglish(input);
    }
}
