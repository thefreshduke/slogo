package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;

public class LeftCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;
	
	public LeftCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}

	@Override
	protected void updateTurtle() {
		
	}

	@Override
	protected double calculateResult() {
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) {
		myDegreesCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myDegreesCommand.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateLeft = myDegreesCommand.execute(view, turtle);
		rotateLeft*=-1;
		turtle.rotate(rotateLeft);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		
		if(nextCommand != null){
		    return nextCommand.execute(view, turtle);
		}
		return rotateLeft;
	}
}