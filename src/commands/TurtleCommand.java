package commands;

import turtle.Turtle;

public abstract class TurtleCommand extends ModelCommand {
	
	public TurtleCommand(String userInput) {
		super(userInput);
	}
	
	@Override
	public double execute(View view, Turtle turtle) {
		updateTurtle();
		sendTurtleToView();
		return calculateResult();
	}
	
	private void sendTurtleToView() {
		// Fire event to front end notifying of turtle update
	}
	
	protected abstract void updateTurtle();
	
	protected abstract double calculateResult();
	
}
