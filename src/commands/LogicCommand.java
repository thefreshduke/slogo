package commands;

import model.CommandWrapper;
import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private CommandWrapper myWrapper;
	
	public LogicCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(CommandWrapper wrapper) throws BackendException {
		myWrapper = wrapper;
//		double result = execute();
//		if(getNextCommand() != null){
//		    return getNextCommand().execute(view, turtle, variableContainer);
//		}
//		return result;
		return execute();
	}
	
	public abstract double execute() throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myWrapper);
	}
}