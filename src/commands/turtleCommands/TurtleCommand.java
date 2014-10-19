package commands.turtleCommands;

import model.CommandWrapper;
import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import turtle.Turtle;

public abstract class TurtleCommand extends BaseCommand {
	private CommandWrapper myWrapper;
	private BaseCommand[] myArgumentList;


	public TurtleCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public final double execute(CommandWrapper wrapper) throws BackendException {
		myWrapper = wrapper;
		BaseCommand nextCommand = getNextCommand();
		return nextCommand == null ? execute(wrapper.getGrid(), wrapper.getTurtle()) : executeCommand(nextCommand);
	}

	public abstract double execute(Grid grid, Turtle turtle) throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myWrapper);
	}

	protected void parseArguments(String userInput) {
		int argumentCount = getArgumentCount();
		if (argumentCount < 0) {
			// TODO: make separate exception
		}
		myArgumentList = new BaseCommand[argumentCount];
		for (int i = 0; i < argumentCount; i++) {
			String subInput;
			if (i == 0) {
				subInput = userInput;
			}
			else {
				subInput = myArgumentList[i - 1].getLeftoverString();
			}
			BaseCommand argument = CommandFactory.createCommand(subInput, true);
			myArgumentList[i] = argument;
		}
		setLeftoverCommands(myArgumentList[myArgumentList.length - 1].getLeftoverString());
	}

	protected BaseCommand[] getExpressionList() {
		return myArgumentList;
	}

	protected abstract int getArgumentCount();

}
