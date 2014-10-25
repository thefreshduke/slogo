package commands.turtleCommands;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

import org.junit.Test;

import turtle.Position;
import turtle.Turtle;
import View.Grid;
import View.SingleGrid;
import View.SlogoView;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.NumericalCommand;
import commands.information.ICommandInformationHub;
import commands.information.SingleViewContainerInformationHub;
import static org.junit.Assert.assertEquals;

// DOESN'T WORK AFTER REDESIGN
public class TurtleCommandsTest {

	@Test
	public void testToParsing () {
		setFactory();
		String input = "FD SUM 50 50 SUM 25 30";
		String input2 = "BK DIFFERENCE 70 20";
		String input3 = "RT 90";
		String input4 = "";
		String input5 = "";
		
		
		
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


			/*String convertedUse = processInput(use);
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
			assertEquals(result3, new Double(2));*/
		}
		catch (BackendException ex) {

		}
	}

	private void setFactory () {
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
			CommandFactory.setCommandToClassRelation(
					commandToClassTranslator.translateCommandToClass(
							new File("src/resources/languages/EnglishToClassName.properties")));
		}
		catch (BackendException ex) {

		}
		return myTranslator.translateUserInputIntoEnglish(input);

	}
}

