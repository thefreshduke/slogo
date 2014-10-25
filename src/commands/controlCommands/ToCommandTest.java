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


public class ToCommandTest {

    @Test
    public void testToParsing () {
        setFactory();
        String command = "To cmd [ :x :y ] [ sum :x :y ]";
        String use = "cmd 3 4";
        try {
            String processedCommand = processInput(command);
            BaseCommand toCommand = CommandFactory.createCommand(processedCommand, false);
            toCommand.execute();
            String convertedUse = processInput(use);
            BaseCommand useCommand = CommandFactory.createCommand(convertedUse, false);
            Double result = useCommand.execute();
            assertEquals(result, new Double(7));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setFactory(){
        Grid grid = new SingleGrid();
        Turtle turtle = new Turtle(new Position(0, 0), null);
        ICommandInformationHub hub = new SingleViewContainerInformationHub(grid, turtle);
        CommandFactory.setInformationHub(hub);
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
