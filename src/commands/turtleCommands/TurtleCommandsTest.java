package commands.turtleCommands;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import org.junit.Test;
import turtle.Position;
import turtle.Turtle;
import View.SlogoView;
import org.junit.Test;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.NumericalCommand;
import static org.junit.Assert.assertEquals;

// DOESN'T WORK AFTER REDESIGN
public class TurtleCommandsTest {

	@Test 
	public void testForward(){
		setFactory();
		String input = "FD constant 50";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try{
			Double result = command.execute();
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
		commandToClassMap.put("constant", NumericalCommand.class);

		CommandFactory.setCommandToClassRelation(commandToClassMap);
	}
}