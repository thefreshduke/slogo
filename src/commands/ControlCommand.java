package commands;

import java.util.Stack;

import model.CommandWrapper;
import communicator.IVariableContainer;
import communicator.MapBasedVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import View.SlogoView;
import turtle.Turtle;

public abstract class ControlCommand extends ModelCommand {
	
    protected static char COMMAND_INDICATOR = '[';
    protected static char COMMAND_END_INDICATOR = ']';
	private CommandWrapper myWrapper;
	
	public ControlCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	// Create static factory to generate commands
	protected void findBrackets(String userInput) {
		//Factory.makeCommand(substring);
		// TODO: Resource File reference to bracket
		
		int beginIndex = userInput.indexOf('[');
		// TODO: Take matching bracket from resource file for 
		// closing bracket
		int endIndex = findLastIndexOfCharacter(userInput, ']');
	}
	
	@Override
	public final double execute(CommandWrapper wrapper) throws BackendException {
		myWrapper = wrapper;
		return execute(new MapBasedVariableContainer());
	}

	protected double executeCommand(BaseCommand command, IVariableContainer variableContainer) throws BackendException{
		return command.execute(myWrapper);
	}
	
	public abstract double execute(IVariableContainer variableContainer) throws BackendException;
	
	protected int findLastIndexOfCharacter(String userInput, char character) {
		for (int i = userInput.length()-1; i >= 0; i--) {
			if (userInput.charAt(i) == character) {
				return i;
			}
		}
		return -1;
	}
	
    protected int findClosingBracketIndex(String input){
        Stack<Character> checkStack = new Stack<>();
        for(int i=0; i < input.length(); i++){
            char character = input.charAt(i);
            if(character == COMMAND_INDICATOR){
                checkStack.push(character);
            }
            else if(character == COMMAND_END_INDICATOR){
                checkStack.pop();
            }
            if(checkStack.size() == 0){
                return i;
            }
        }
        return -1;
    }
}