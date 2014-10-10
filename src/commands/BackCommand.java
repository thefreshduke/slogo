package commands;

import turtle.Turtle;
import View.SlogoView;

public class BackCommand extends TurtleCommand {

	private BaseCommand myPixelsCommand;
	
	public BackCommand(String userInput, boolean isExpression) {
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
		double movedDistance = myPixelsCommand.execute(view, turtle);
		movedDistance*=-1.0;
		turtle.move(movedDistance);
		view.update(turtle.getXPos(), turtle.getYPos());
		return movedDistance;
	}
}