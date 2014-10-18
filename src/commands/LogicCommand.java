package commands;

import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private Grid myGrid;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	
	public LogicCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		myGrid = grid;
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
		return command.execute(myGrid, myTurtle, myVariableContainer);
	}
}