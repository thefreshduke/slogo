package commands.turtleCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import turtle.Turtle;
import View.SlogoView;

public class BackCommand extends TurtleCommand {

	private BaseCommand myPixelsCommand;

	public BackCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void parseArguments(String userInput) {
		myPixelsCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myPixelsCommand.getLeftoverString());
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double movedDistance = executeCommand(myPixelsCommand);
		turtle.move(movedDistance*-1);
		view.update(turtle.getXPos(), turtle.getYPos());
		return movedDistance;
	}
}