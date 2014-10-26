package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import commands.information.BaseTurtleContainer;
import commands.information.IInformationGateway;
import commands.information.IInformationContainer;
import commands.information.SingleViewInformationGateway;

public class AskWithTest {

	@Test
	public void testAlwaysTrueAskWith() throws BackendException{
		IInformationGateway infoHub = setFactory();
		String askWithCommandString = "ASKWITH [ GREATERP 51 50 ] [ FD 50 ]";
		String convertedAskWithCommandString = processInput(askWithCommandString);
		BaseCommand askWithCommand = CommandFactory.createCommand(convertedAskWithCommandString, false);
		askWithCommand.execute();
		BaseTurtleContainer turtleContainer = (BaseTurtleContainer)infoHub.getContainer(BaseTurtleContainer.class);
		Collection<Turtle> turtles = turtleContainer.getAllTurtles();
		for(Turtle turtle : turtles){
			Double xPos = turtle.getXPos();
			if(turtle.getID() == 1){
				assertEquals(xPos, new Double(50));
			}
			if(turtle.getID() == 2) {
				assertEquals(xPos, new Double(101));
			}
		}
	}
	
	@Test
	public void testXCorAskWith() throws BackendException{
		IInformationGateway infoHub = setFactory();
		String askWithCommandString = "ASKWITH [ GREATERP XCOR 50 ] [ FD 50 ]";
		String convertedAskWithCommandString = processInput(askWithCommandString);
		BaseCommand askWithCommand = CommandFactory.createCommand(convertedAskWithCommandString, false);
		askWithCommand.execute();
		BaseTurtleContainer turtleContainer = (BaseTurtleContainer)infoHub.getContainer(BaseTurtleContainer.class);
		Collection<Turtle> turtles = turtleContainer.getAllTurtles();
		for(Turtle turtle : turtles){
			Double xPos = turtle.getXPos();
			if(turtle.getID() == 1){
				assertEquals(xPos, new Double(0));
			}
			if(turtle.getID() == 2) {
				assertEquals(xPos, new Double(101));
			}
		}
	}
	
    private IInformationGateway setFactory () {
        Grid grid = new SingleGrid();
        Turtle turtle = new Turtle(new Position(0, 0, 0), null);
        turtle.setID(1);
        Turtle turtle2 = new Turtle(new Position(51, 0, 0), null);
        turtle2.setID(2);
        IInformationGateway hub = new SingleViewInformationGateway(grid, turtle);
        BaseTurtleContainer turtleContainer = (BaseTurtleContainer)hub.getContainer(BaseTurtleContainer.class);
        turtleContainer.addTurtle(turtle2, false);
        CommandFactory.setInformationGateway(hub);
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
