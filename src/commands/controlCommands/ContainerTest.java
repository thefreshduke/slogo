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
import commands.information.IInformationContainer;
import commands.information.SingleViewContainerInformationHub;

public class ContainerTest {

	@Test
	public void testThatIfWorks(){
		String testString = "if sum 50 50 [ fd 50 ]";
		String translated;
		try {
			translated = processInput(testString);
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseCommand command = CommandFactory.createCommand(testString, false);
		Double result = null;
		try {
			result = new Double(command.execute());
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(result, new Double(50.0));
	}
	
	private String processInput(String input) throws BackendException {
		Grid grid = new SingleGrid();
		Turtle turtle = new Turtle(new Position(0,0), null);
		ICommandInformationHub hub = new SingleViewContainerInformationHub(grid, turtle);
		LanguageFileParser myTranslator = new LanguageFileParser(new File("src/resources/languages/English.properties"));
		CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
		try {
			CommandFactory.setInformationHub(hub);
			CommandFactory
			.setCommandToClassRelation(commandToClassTranslator
					.translateCommandToClass(new File("src/resources/languages/EnglishToClassName.properties")));
		}
		catch (BackendException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return   myTranslator.translateUserInputIntoEnglish(input);
	}
}
