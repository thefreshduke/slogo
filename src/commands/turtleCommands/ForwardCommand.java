package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;
import turtle.Turtle;
import View.Grid;
import View.SlogoView;

public class ForwardCommand extends TurtleCommand {

	public ForwardCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	public double execute(Grid grid, Turtle turtle) throws BackendException {
		double movedDistance = executeCommand(getExpressionList()[0]);
		turtle.move(movedDistance);
		List<Turtle> turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
		grid.update(turtleList);
		return movedDistance;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}
}
