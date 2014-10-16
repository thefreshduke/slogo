package commands.turtleCommands;


import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class RightCommand extends TurtleCommand {

	public RightCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateRight = executeCommand(getExpressionList()[0]);
		turtle.rotate(rotateRight);
		view.update(turtle.getXPos(), turtle.getYPos());
		return rotateRight;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}