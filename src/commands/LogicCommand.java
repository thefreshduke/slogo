package commands;

import java.util.List;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private SlogoView myView;
	private Turtle myTurtle;
	
	public LogicCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(SlogoView view, Turtle turtle) throws BackendException {
		myView = view;
		myTurtle = turtle;
		return execute();
	}
	
	public abstract double execute() throws BackendException;

	protected double executeCommand(BaseCommand command) throws BackendException{
		return command.execute(myView, myTurtle);
	}
}