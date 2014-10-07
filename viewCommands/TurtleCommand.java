package viewCommands;

import turtle.Turtle;

public abstract class TurtleCommand extends ModelCommand {
	
	public TurtleCommand(String userInput) {
		this(userInput);

	}
	public TurtleCommand(String userInput) {
		super(userInput);
		parseArguments(userInput);
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
	public abstract void updateTurtle();
	public abstract double calculateResult();
	
}
