package viewCommands;

import turtle.Turtle;

public abstract class TurtleCommand extends ModelCommand {

	private Turtle myTurtle;
	
	public TurtleCommand(String userInput, Turtle turtle) {
		this(userInput);
		myTurtle = turtle;
	}
	public TurtleCommand(String userInput) {
		super(userInput);
		parseArguments(userInput);
	}
	
	@Override
	public double executeCommand() {
		updateTurtle(myTurtle);
		return calculateResult();
	}
	
	public abstract double calculateResult();
	
}
