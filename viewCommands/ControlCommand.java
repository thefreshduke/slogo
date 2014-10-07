package viewCommands;

import java.util.List;

public abstract class ControlCommand extends ModelCommand {
	public ControlCommand(String userInput) {
		super(userInput);
	}
	
	// Create static factory to generate commands
	private void findBrackets(String userInput) {
		//Factory.makeCommand(substring);
		// TODO: Resource File reference to bracket
		
		int beginIndex = userInput.indexOf('[');
		// TODO: Take matching bracket from resource file for 
		// closing bracket
		int endIndex = findLastIndexOfCharacter(userInput, ']');
	}
	
	@Override
	public final double execute(View view, Turtle turtle) {
		return execute();
	}

	public abstract int execute();
	


	
	private int findLastIndexOfCharacter(String userInput, char character) {
		for (int i = userInput.length()-1; i >= 0; i--) {
			if (userInput.charAt(i) == character) {
				return i;
			}
		}
		return -1;
	}
	
}
