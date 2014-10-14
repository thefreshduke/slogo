package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;

public class TowardsCommand extends TurtleCommand {

	private BaseCommand myFirstExpression;
	private BaseCommand mySecondExpression;

	public TowardsCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	protected void parseArguments (String userInput) {
		BaseCommand firstExpression = CommandFactory.createCommand(userInput, true);
		myFirstExpression = firstExpression;
		BaseCommand secondExpression = CommandFactory.createCommand(firstExpression.getLeftoverString(), true);
		mySecondExpression = secondExpression;
		setLeftoverCommands(mySecondExpression.getLeftoverString());

	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double currentXPos = turtle.getXPos();
		double currentYPos = turtle.getYPos();
		double newXPos = myFirstExpression.execute(view, turtle, null);
		double newYPos = mySecondExpression.execute(view, turtle, null);
		double angleRadians = Math.atan(((currentYPos-newYPos)/ (currentXPos - newXPos)));
		double angleDegrees = Math.toDegrees(angleRadians);
		System.out.println(angleDegrees);

		//TODO: Figure out if angle computations work out correctly
		turtle.setRotation(angleDegrees);
		return angleDegrees;
	}
}