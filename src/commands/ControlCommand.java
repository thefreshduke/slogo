package commands;

import java.util.Stack;

import commands.information.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class ControlCommand extends ModelCommand {
	
    protected static String COMMAND_INDICATOR = "liststart";
    protected static String COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
	private SlogoView myView;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	
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
	public final double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		myView = view;
		myTurtle = turtle;
		myVariableContainer = variableContainer;
		return execute(null);
	}

	protected double executeCommand(BaseCommand command, IVariableContainer variableContainer) throws BackendException{
		return command.execute(myView, myTurtle, myVariableContainer);
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
        Stack<String> checkStack = new Stack<>();
        StringBuilder temporaryStringBuilder = new StringBuilder();
        for(int i=0; i < input.length(); i++){
            Character character = input.charAt(i);
            temporaryStringBuilder.append(character);
            if(COMMAND_SEPARATOR.equals(character.toString()) || i == input.length()-1){
            	String aggregatedWord = temporaryStringBuilder.toString().trim();
            	if(aggregatedWord.equals(COMMAND_INDICATOR)){
            		checkStack.push(aggregatedWord);
            	}
            	else if(aggregatedWord.equals(COMMAND_END_INDICATOR)){
                    checkStack.pop();
                }
            	if(checkStack.size() == 0){
                    return i;
                }
            	temporaryStringBuilder.setLength(0);
            }
        }
        return -1;
    }
    
    protected boolean startsWithCommandStartIndicator(String input) {
    	String splitInput = input.trim().split(COMMAND_SEPARATOR, 2)[0];
    	return splitInput.equals(COMMAND_INDICATOR);
    }
}
