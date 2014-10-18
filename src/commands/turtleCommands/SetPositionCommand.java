package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class SetPositionCommand extends TurtleCommand {

	public SetPositionCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double currentXPos = turtle.getXPos();
		double currentYPos = turtle.getYPos();
		turtle.setXPos(executeCommand(getExpressionList()[0]));
		turtle.setYPos(executeCommand(getExpressionList()[1]));
		view.update(turtle.getXPos(), turtle.getYPos());
		double distance = Math.sqrt(Math.pow(currentXPos-turtle.getXPos(), 2) + Math.pow(currentYPos - turtle.getYPos(), 2));
		return distance;
	}

	@Override
	protected int getArgumentCount() {
		return 2;
	}
}