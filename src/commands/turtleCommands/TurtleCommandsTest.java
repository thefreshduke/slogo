package commands.turtleCommands;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import org.junit.Test;
import turtle.Position;
import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import static org.junit.Assert.assertEquals;

// DOESN'T WORK AFTER REDESIGN
public class TurtleCommandsTest {

	@Test 
	public void testForward(){
		setFactory();
		String input = "FD 50";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try{
			Double result = command.execute(new SlogoView(), new Turtle(new Position(0, 0), new Image("bowser.png")), null);
			assertEquals(result, new Double(50));
		}
		catch(BackendException ex){

		}
	}
	
	private void setFactory(){
		Map<String, Class> commandToClassMap = new HashMap<>();
		commandToClassMap.put("LEFT", LeftCommand.class);
		commandToClassMap.put("RIGHT", RightCommand.class);
		commandToClassMap.put("FD", ForwardCommand.class);
		commandToClassMap.put("BK", BackCommand.class);

		CommandFactory.setCommandToClassRelation(commandToClassMap);
	}
}