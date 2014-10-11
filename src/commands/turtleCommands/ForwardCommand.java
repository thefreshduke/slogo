package commands.turtleCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import turtle.Turtle;
import View.SlogoView;

public class ForwardCommand extends TurtleCommand {

	private BaseCommand myDistance;

	public ForwardCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void parseArguments(String userInput) {
		BaseCommand command = CommandFactory.createCommand(userInput, true);
		myDistance = command;
		setLeftoverCommands(command.getLeftoverString());
	}

	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double movedDistance = executeCommand(myDistance);
		turtle.move(movedDistance);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		if(nextCommand != null){
			return executeCommand(nextCommand);
		}
		return movedDistance;
	}
}
