package commands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import communicator.IVariableContainer;
import turtle.Turtle;
import View.SlogoView;

public class BackCommand extends TurtleCommand {

	private BaseCommand myPixelsCommand;

	public BackCommand(String userInput, boolean isExpression) throws BackendException {
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
		myPixelsCommand = CommandFactory.createCommand(userInput, true);
		setLeftoverCommands(myPixelsCommand.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		double movedDistance = myPixelsCommand.execute(view, turtle, null);
		movedDistance*=-1.0;
		turtle.move(movedDistance);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		if(nextCommand != null){
			return nextCommand.execute(view, turtle, null);
		}
		return movedDistance;
	}
}