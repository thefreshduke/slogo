package commands.turtleCommands;

import commands.BaseCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class TurtleCommand extends BaseCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;

	public TurtleCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}


	@Override
	public final double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		myView = view;
		myTurtle = turtle;
		myVariableContainer = variableContainer;
		//	double result = execute();
		//	if(getNextCommand() != null){
		//	    return getNextCommand().execute(view, turtle, variableContainer);
		//	}
		//	return result;
		return execute(view, turtle);
	}

	public abstract double execute(SlogoView view, Turtle turtle) throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myView, myTurtle, myVariableContainer);
	}
}
