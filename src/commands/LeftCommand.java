package commands;

import turtle.Turtle;
import View.SlogoView;

public class LeftCommand extends TurtleCommand {

	private BaseCommand myPixelsCommand;
	
	public LeftCommand(String userInput, boolean isExpression) {
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
		myPixelsCommand = TestFactory.createCommand(userInput, true);
		setLeftoverCommands(myPixelsCommand.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) {
		double rotateLeft = myPixelsCommand.execute(view, turtle);
		rotateLeft*=-1;
		turtle.rotate(rotateLeft);
		view.update(turtle.getXPos(), turtle.getYPos());
		return rotateLeft;
	}
}