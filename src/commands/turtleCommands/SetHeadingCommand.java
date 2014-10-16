package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double currentRotate = turtle.getOrientation();
		double absoluteRotate = executeCommand(getExpressionList()[0]);
		turtle.setRotation(absoluteRotate);
		view.update(turtle.getXPos(), turtle.getYPos());
		return (absoluteRotate - currentRotate);
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}