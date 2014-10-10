package commands;

import java.util.List;

import View.SlogoView;
import turtle.Turtle;

public abstract class LogicCommand extends ModelCommand {
	private List<String> myExpressionArguments; 
	private SlogoView myView;
	private Turtle myTurtle;
	
	public LogicCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}
	
	@Override
	public final double execute(SlogoView view, Turtle turtle) {
		myView = view;
		myTurtle = turtle;
		return execute();
	}
	
	public abstract double execute();

	protected double executeCommand(BaseCommand command){
		return command.execute(myView, myTurtle);
	}
	
	protected List<String> getExpressionArguments() {
		return myExpressionArguments;
	}

	protected void setExpressionArguments(List<String> expressionArguments) {
		myExpressionArguments = expressionArguments;
	}
}