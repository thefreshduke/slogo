package commands;

import java.util.List;

import commands.information.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	
	public LogicCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	public abstract double execute() throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute();
	}
}