package commands;

import java.util.List;

import commands.information.BaseVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	private BaseVariableContainer myVariableContainer;
	
	public LogicCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	protected abstract double onExecute() throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.onExecute();
	}
}