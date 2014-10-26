package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

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
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.information.ICommandInformationHub;
import commands.information.SingleViewContainerInformationHub;

public class AskTest {

	@Test
	public void testThatAskCreatesTurtles() throws BackendException{
		ICommandInformationHub infoHub = setFactory();
		String askCommandString = "ASK [ 0 ] [ FD 50 ]";
		String convertedAskCommand = processInput(askCommandString);
		BaseCommand askCommand = CommandFactory.createCommand(convertedAskCommand, false);
		askCommand.execute();
		BaseTurtleContainer turtleContainer = (BaseTurtleContainer)infoHub.getContainer(BaseTurtleContainer.class);
		Collection<Turtle> turtles = turtleContainer.getAllTurtles();
		assertEquals(turtles.size() > 0, true);
	}
	
    private ICommandInformationHub setFactory () {
        Grid grid = new SingleGrid();
        ICommandInformationHub hub = new SingleViewContainerInformationHub();
        BaseGridContainer gridContainer = (BaseGridContainer)hub.getContainer(BaseGridContainer.class);
        gridContainer.addGrid(grid, true);
        CommandFactory.setInformationHub(hub);
        return hub;
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
