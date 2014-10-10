package commands;

import turtle.Turtle;
import View.SlogoView;

public class ForwardCommand extends TurtleCommand {

	private BaseCommand myDistance;
	
	public ForwardCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateTurtle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected double calculateResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) {
		BaseCommand command = TestFactory.createCommand(userInput, true);
		setLeftoverCommands(command.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle) {
		double movedDistance = myDistance.execute(view, turtle);
		turtle.moveVertical(movedDistance);
		view.updateTurtle(turtle.getX(), turtle.getY());
	}



}
