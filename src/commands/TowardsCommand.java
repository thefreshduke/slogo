package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;

public class TowardsCommand extends TurtleCommand {

	private BaseCommand myFirstExpression;
	private BaseCommand mySecondExpression;

	public TowardsCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}

	@Override
	protected void updateTurtle() {

	}

	@Override
	protected double calculateResult() {
		return 0;
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
		double newXPos = myFirstExpression.execute(view, turtle);
		double newYPos = mySecondExpression.execute(view, turtle);
		double angleRadians = Math.atan(((currentYPos-newYPos)/ (currentXPos - newXPos)));
		double angleDegrees = Math.toDegrees(angleRadians);
		System.out.println(angleDegrees);

		//TODO: Figure out if angle computations work out correctly
		turtle.setRotation(angleDegrees);
		BaseCommand nextCommand = getNextCommand();
		if(nextCommand != null){
			return nextCommand.execute(view, turtle);
		}
		return angleDegrees;
	}
}