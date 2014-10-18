package commands;

import java.util.List;

import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import View.SlogoView;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	
	public LogicCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		myView = grid;
		myTurtle = turtle;
		myVariableContainer = variableContainer;
//		double result = execute();
//		if(getNextCommand() != null){
//		    return getNextCommand().execute(view, turtle, variableContainer);
//		}
//		return result;
		return execute();
	}
	
	public abstract double execute() throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myView, myTurtle, myVariableContainer);
	}
}