package commands.viewCommands;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.ViewCommand;
import communicator.IVariableContainer;

public class HomeCommand extends ViewCommand {

	public HomeCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		double initialXPos = turtle.getXPos();
		double initialYPos = turtle.getYPos();
		turtle.setXPos(0); //need width/2 from front end
		turtle.setYPos(0); //need height/2 from front end
		grid.update(turtle.getXPos(), turtle.getYPos());
		System.out.println("Home: " + Math.sqrt(Math.pow(turtle.getXPos() - initialXPos, 2) + Math.pow(turtle.getYPos() - initialYPos, 2)));
		return Math.sqrt(Math.pow(turtle.getXPos() - initialXPos, 2) + Math.pow(turtle.getYPos() - initialYPos, 2));
		//trail to home or no trail?
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
