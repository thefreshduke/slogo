package commands.viewCommands;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;

public class ClearScreenCommand extends ViewCommand {

	public ClearScreenCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute() throws BackendException {
		double initialXPos = turtle.getXPos();
		double initialYPos = turtle.getYPos();
		turtle.setXPos(0); //need width/2 from front end
		turtle.setYPos(0); //need height/2 from front end
		view.update(turtle.getXPos(), turtle.getYPos());
		System.out.println("Clear Screen: " + Math.sqrt(Math.pow(turtle.getXPos() - initialXPos, 2) + Math.pow(turtle.getYPos() - initialYPos, 2)));
		return Math.sqrt(Math.pow(turtle.getXPos() - initialXPos, 2) + Math.pow(turtle.getYPos() - initialYPos, 2));
		//no trail to home
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
