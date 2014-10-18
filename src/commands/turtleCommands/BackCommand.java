package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class BackCommand extends TurtleCommand {


	public BackCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double movedDistance = executeCommand(getExpressionList()[0]);
		turtle.move(movedDistance*-1);
		view.update(turtle.getXPos(), turtle.getYPos());
		return movedDistance;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}