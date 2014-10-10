package commands;

import View.SlogoView;
import turtle.Turtle;

public abstract class TurtleCommand extends ModelCommand {
	
	public TurtleCommand(String userInput, boolean isExpression) {
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
