package commands;

import java.util.Stack;

import View.SlogoView;
import turtle.Turtle;

public abstract class ControlCommand extends ModelCommand {
	
	private SlogoView myView;
	private Turtle myTurtle;
	
	public ControlCommand(String userInput, boolean isExpression) {
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
	public final double execute(SlogoView view, Turtle turtle) {
		myView = view;
		myTurtle = turtle;
		return execute();
	}

	protected double executeCommand(BaseCommand command){
		return command.execute(myView, myTurtle);
	}
	
	public abstract double execute();
	
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
            if(character == '['){
                checkStack.push(character);
            }
            else if(character == ']'){
                checkStack.pop();
            }
            if(checkStack.size() == 0){
                return i;
            }
        }
        return -1;
    }
}
