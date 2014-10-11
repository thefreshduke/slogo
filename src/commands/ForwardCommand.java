package commands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import communicator.IVariableContainer;
import turtle.Turtle;
import View.SlogoView;

public class ForwardCommand extends TurtleCommand {

	private BaseCommand myDistance;
	
	public ForwardCommand(String userInput, boolean isExpression) throws BackendException {
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
		BaseCommand command = CommandFactory.createCommand(userInput, true);
		myDistance = command;
		setLeftoverCommands(command.getLeftoverString());// TODO Auto-generated method stub
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		double movedDistance = myDistance.execute(view, turtle, variableContainer);
		turtle.move(movedDistance);
		view.update(turtle.getXPos(), turtle.getYPos());
		BaseCommand nextCommand = getNextCommand();
		if(nextCommand != null){
		    return nextCommand.execute(view, turtle, variableContainer);
		}
		return movedDistance;
	}
}
