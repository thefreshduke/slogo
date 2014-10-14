package commands.turtleCommands;

import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class RightCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;

	public RightCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void parseArguments(String userInput) {
		myDegreesCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myDegreesCommand.getLeftoverString());
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateRight = myDegreesCommand.execute(view, turtle, null);
		turtle.rotate(rotateRight);
		view.update(turtle.getXPos(), turtle.getYPos());
		return rotateRight;
	}
}