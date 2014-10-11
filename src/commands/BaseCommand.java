package commands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import communicator.IVariableContainer;
import View.SlogoView;
import turtle.Turtle;

/**
 * 
 * Abstract class for a Turtle command.  The update method will be implemented and
 * returns the turtle object that can be manipulated by other commands. All other 
 * types of commands will extend the BaseCommand, such as commands that only 
 * modify the view, commands that are conditional, mathematical operations. 
 *
 */
public abstract class BaseCommand {
	private BaseCommand myNextCommand;
	private BaseCommand myInternalCommand;
	private String myLeftoverString = "";
	private boolean myExpressionFlag;
    protected final String COMMAND_DELIMITER = "\\s+";
	/**
	 * 
	 * @param userInput
	 * @throws BackendException TODO
	 */
	public BaseCommand(String userInput, boolean isExpression) throws BackendException {
	    myExpressionFlag = isExpression;
	    parseArguments(userInput);
	}

	/**
	 * Method returns the computation of the turtle command
	 * @param variableContainer TODO
	 * @throws BackendException TODO
	 * 
	 */
	public abstract double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException;

	protected BaseCommand getNextCommand(){
		return myNextCommand;
	}

	protected abstract void parseArguments(String userInput) throws BackendException;
	
	protected void setNextCommand(BaseCommand command){
		myNextCommand = command;
	}
	
	protected BaseCommand getInternalCommand(){
		return myInternalCommand;
	}
	
	protected void setInternalCommand(BaseCommand command){
		myInternalCommand = command;
	}
	
	public String getLeftoverString(){
	    return myLeftoverString;
	}
	
	protected void setLeftoverCommands(String string){
	    if(myExpressionFlag){
	        myLeftoverString = string;
	    }
	    else if(string != null && string != ""){
	    	BaseCommand command = CommandFactory.createCommand(string, false);
	        myNextCommand = command;
	    }
	}
}
