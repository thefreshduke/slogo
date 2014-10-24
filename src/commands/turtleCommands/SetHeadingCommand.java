package commands.turtleCommands;

import commands.information.BaseTurtleContainer;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	public double onExecute() throws BackendException {
		BaseTurtleContainer turtleContainer = getTurtleContainer();
		
		double currentRotate = turtle.getOrientation();
		double absoluteRotate = getExpressionList()[0].execute();
		turtle.setRotation(absoluteRotate);
		view.update(turtle.getXPos(), turtle.getYPos());
		return (absoluteRotate - currentRotate);
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}