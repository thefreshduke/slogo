// This entire file is part of my masterpiece.
// Rahul Harikrishnan
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
 * Testing different turtle commands based on different types of commands that could be used 
 * as expressions and different expression counts. Ensuring that parsing algorithm
 * and changes made to TurtleCommandClass doesn't break code.
 * @author Rahul
 *
 */
public class TurtleCommandsTest {

	@Test
	public void testToParsing () {
		setFactory();
		String input = "FD SUM 75 25";
		String input2 = "FD DIFFERENCE 125 50";
		String input3 = "FD IFELSE SUM 50 50 [ RT 90 ] [ RT 25 ]";
		String input4 = "FD IFELSE SUM 0 0 [ RT 90 ] [ RT 25 ]";
		String input5 = "FD IF DIFFERENCE 15 2 [ FD SUM 25 FD SUM 12 13 ]";
		String input6 = "FD GOTO 400 0";
		String input7 = "SETXY 150 250";
		String input8 = "TOWARDS 0 0";

		try {
			String processedCommand = processInput(input);
			BaseCommand command = CommandFactory.createCommand(processedCommand, false);
			Double result = command.execute();
			assertEquals(new Double(100), result); 

			processedCommand = processInput(input2);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(75), result); 

			processedCommand = processInput(input3);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(90), result); 

			processedCommand = processInput(input4);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(25), result);

			processedCommand = processInput(input5);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(50), result);

			processedCommand = processInput(input6);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(353.553390593), result);

			processedCommand = processInput(input7);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(250), result);

			processedCommand = processInput(input8);
			command = CommandFactory.createCommand(processedCommand, false);
			result = command.execute();
			assertEquals(new Double(0), result);

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
			System.out.println("Could not set command to class relation");
		}
		return myTranslator.translateUserInputIntoEnglish(input);

	}
}