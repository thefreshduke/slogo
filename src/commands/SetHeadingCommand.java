package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;
import communicator.IVariableContainer;

public class SetHeadingCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;

	public SetHeadingCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void updateTurtle() {

	}

	@Override
	protected double calculateResult() {
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) {
		myDegreesCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myDegreesCommand.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		double currentRotate = turtle.getOrientation();
		double absoluteRotate = myDegreesCommand.execute(view, turtle, null);
		turtle.setRotation(absoluteRotate);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();

		if(nextCommand != null){
			return nextCommand.execute(view, turtle, null);
		}
		return (absoluteRotate - currentRotate);
	}
}