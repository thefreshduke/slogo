package commands.turtleCommands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;
import commands.BaseCommand;

public class LeftCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;

	public LeftCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void parseArguments(String userInput) {
		myDegreesCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myDegreesCommand.getLeftoverString());
	}

	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double rotateLeft = executeCommand(myDegreesCommand);
		rotateLeft*=-1;
		turtle.rotate(rotateLeft);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();

		if(nextCommand != null){
			return executeCommand(nextCommand);
		}
		return rotateLeft;
	}
}