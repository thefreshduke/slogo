package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class TowardsCommand extends TurtleCommand {

	public TowardsCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double currentXPos = turtle.getXPos();
		double currentYPos = turtle.getYPos();
		double newXPos = executeCommand(getExpressionList()[0]);
		double newYPos = executeCommand(getExpressionList()[1]);
		double angleRadians = Math.atan(((currentYPos-newYPos)/ (currentXPos - newXPos)));
		double angleDegrees = Math.toDegrees(angleRadians);
		System.out.println(angleDegrees);

		//TODO: Figure out if angle computations work out correctly
		turtle.setRotation(angleDegrees);
		return angleDegrees;
	}

	@Override
	protected int getArgumentCount() {
		return 2;
	}
}