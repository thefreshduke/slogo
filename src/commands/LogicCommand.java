package commands;

import java.util.List;

import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private List<String> myExpressionArguments; 

	public LogicCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(View view, Turtle turtle) {
		return execute();
	}

	public abstract double execute();

	protected List<String> getExpressionArguments() {
		return myExpressionArguments;
	}

	protected void setExpressionArguments(List<String> expressionArguments) {
		myExpressionArguments = expressionArguments;
	}
}