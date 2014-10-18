package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class LeftCommand extends TurtleCommand {

	public LeftCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateLeft = executeCommand(getExpressionList()[0]);
		turtle.rotate(rotateLeft*-1);
		view.update(turtle.getXPos(), turtle.getYPos());
		return rotateLeft;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}