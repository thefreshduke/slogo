package commands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
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
	/**
	 * 
	 * @param userInput
	 */
	public BaseCommand(String userInput, boolean isExpression) {
	    myExpressionFlag = isExpression;
	    parseArguments(userInput);
	}

	/**
	 * Method returns the computation of the turtle command
	 * @throws BackendException TODO
	 * 
	 */
	public abstract double execute(SlogoView view, Turtle turtle) throws BackendException;

	protected BaseCommand getNextCommand(){
		return myNextCommand;
	}

	protected abstract void parseArguments(String userInput);
	
	protected void setNextCommand(BaseCommand command){
		myNextCommand = command;
	}
	
	protected BaseCommand getInternalCommand(){
		return myInternalCommand;
	}
	
	protected void setInternalCommand(BaseCommand command){
		myInternalCommand = command;
	}
	
	protected String getLeftoverString(){
	    return myLeftoverString;
	}
	
	protected void setLeftoverCommands(String string){
	    if(myExpressionFlag){
	        myLeftoverString = string;
	    }
	    else if(string != null || string != ""){
	        myNextCommand = CommandFactory.createCommand(string, false);
	    }
	}
}
