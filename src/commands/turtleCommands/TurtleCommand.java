package commands.turtleCommands;

import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class TurtleCommand extends BaseCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	private BaseCommand[] myArgumentList;


	public TurtleCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public final double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		myView = view;
		myTurtle = turtle;
		myVariableContainer = variableContainer;
		BaseCommand nextCommand = getNextCommand();
		return nextCommand == null ? execute(view, turtle) : executeCommand(nextCommand);
	}

	public abstract double execute(SlogoView view, Turtle turtle) throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myView, myTurtle, myVariableContainer);
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
