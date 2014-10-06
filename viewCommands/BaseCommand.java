package viewCommands;

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
	private boolean myBackEndFlag;
	private boolean myFrontEndFlag; 
	
	/**
	 * 
	 * @param userInput
	 */
	public BaseCommand(String userInput) {
		setFlags();
	}
	
	/**
	 * Method takes in a the turtle and updates it
	 * @param turtle to be updated
	 * 
	 */
	public abstract void updateTurtle(Turtle turtle);
	
	/**
	 * Method returns the computation of the turtle command
	 * 
	 */
	public abstract double executeCommand();
	
	protected void setBackEndFlag(boolean flag) {
		myBackEndFlag = flag;
	}
	
	protected void setFrontEndFlag(boolean flag) {
		myFrontEndFlag = flag;
	}
	
	public boolean getBackEndFlag() {
		return myBackEndFlag;
	}
	
	public boolean getFrontEndFlag() {
		return myFrontEndFlag;
	}
	
	public abstract void setFlags();

}
