package commands;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;
import commandParser.CommandFactory;

public class SetHeadingCommand extends TurtleCommand {

	private BaseCommand myDegreesCommand;

	public SetHeadingCommand(String userInput, boolean isExpression) {
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
	public double execute(SlogoView view, Turtle turtle) throws BackendException {
		double currentRotate = turtle.getOrientation();
		double absoluteRotate = myDegreesCommand.execute(view, turtle);
		turtle.setRotation(absoluteRotate);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();

		if(nextCommand != null){
			return nextCommand.execute(view, turtle);
		}
		return (absoluteRotate - currentRotate);
	}
}