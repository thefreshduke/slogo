package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;
import commands.BaseCommand;

public class SetPositionCommand extends TurtleCommand {

	private BaseCommand myFirstExpression;
	private BaseCommand mySecondExpression;

	public SetPositionCommand(String userInput, boolean isExpression) throws BackendException {
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
		turtle.setXPos(executeCommand(myFirstExpression));
		turtle.setYPos(executeCommand(mySecondExpression));
		view.update(turtle.getXPos(), turtle.getYPos());
		double distance = Math.sqrt(Math.pow(currentXPos-turtle.getXPos(), 2) + Math.pow(currentYPos - turtle.getYPos(), 2));
		return distance;
	}
}