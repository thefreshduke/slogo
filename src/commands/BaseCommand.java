package commands;

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
	/**
	 * 
	 * @param userInput
	 */
	public BaseCommand(String userInput) {
		parseArguments(userInput);
	}

	/**
	 * Method returns the computation of the turtle command
	 * 
	 */
	public abstract double execute(View view, Turtle turtle);

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
}
