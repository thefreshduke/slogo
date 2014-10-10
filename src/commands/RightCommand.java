package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class RightCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;
	
	public RightCommand(String userInput, boolean isExpression) {
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
		myDegreesCommand = TestFactory.createCommand(userInput, true);
		setLeftoverCommands(myDegreesCommand.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateRight = myDegreesCommand.execute(view, turtle);
		turtle.rotate(rotateRight);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		
		if(nextCommand != null){
		    return nextCommand.execute(view, turtle);
		}
		return rotateRight;
	}
}