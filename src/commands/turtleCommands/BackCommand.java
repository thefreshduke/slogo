package commands.turtleCommands;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import backendExceptions.BackendException;
import turtle.Turtle;
import View.SlogoView;

public class BackCommand extends TurtleCommand {


	public BackCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double onExecute() throws BackendException {
		BaseGridContainer gridContainer = getGridContainer();
		BaseTurtleContainer turtle = getTurtleContainer();
		double movedDistance = getExpressionList()[0].execute();
		turtle.moveTowardsHeading(movedDistance*-1);
		gridContainer.update(turtle.getActiveTurtles());
		return movedDistance;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}