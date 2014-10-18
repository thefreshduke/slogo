package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class ForwardCommand extends TurtleCommand {

	public ForwardCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double movedDistance = executeCommand(getExpressionList()[0]);
		turtle.move(movedDistance);
		view.update(turtle.getXPos(), turtle.getYPos());
		return movedDistance;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}
