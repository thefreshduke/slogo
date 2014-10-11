package commands;

import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public abstract class TurtleCommand extends ModelCommand {
	
	public TurtleCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
//	@Override
//	public double execute(SlogoView view, Turtle turtle) {
//	    updateTurtle();
//	    sendTurtleToView();
//	    return calculateResult();
//	}
//	
	private void sendTurtleToView() {
		
	}
	
	protected abstract void updateTurtle();
	
	protected abstract double calculateResult();
	
}
