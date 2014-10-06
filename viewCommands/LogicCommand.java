package viewCommands;

import java.util.List;

import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private List<String> myExpressionArguments; 

	public LogicCommand(String userInput) {
		super(userInput);
		parseArguments(userInput);
	}
	
	@Override 
	public final void updateTurtle(Turtle turtle) {
		return;
	}

	protected List<String> getExpressionArguments() {
		return myExpressionArguments;
	}

	protected void setExpressionArguments(List<String> expressionArguments) {
		myExpressionArguments = expressionArguments;
	}
}