package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;

public class SetPositionCommand extends TurtleCommand {

	private BaseCommand myFirstExpression;
	private BaseCommand mySecondExpression;

	public SetPositionCommand(String userInput, boolean isExpression) {
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
		turtle.setXPos(myFirstExpression.execute(view, turtle));
		turtle.setYPos(mySecondExpression.execute(view, turtle));
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		if(nextCommand != null){
			return nextCommand.execute(view, turtle);
		}
		double distance = Math.sqrt(Math.pow(currentXPos-turtle.getXPos(), 2) + Math.pow(currentYPos - turtle.getYPos(), 2));
		return distance;
	}
}