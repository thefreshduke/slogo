package commands.viewCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.ViewQuery;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class HomeCommand extends TurtleQuery {

    private static final String EXACTLY_ONE_ACTIVE_GRID_IS_NOT_SET = "Exactly one active grid is not set";

    public HomeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        BaseTurtleContainer turtle = getTurtleContainer();
        List<Grid> gridList = (ArrayList<Grid>) grid.getActiveGrids();
        if (gridList.size() != 1) {
        	throw new BackendException(null, EXACTLY_ONE_ACTIVE_GRID_IS_NOT_SET);
        }
        Grid activeGrid = gridList.get(0);
        double distanceTraveled = turtle.setPosition(activeGrid.getWidth()/2, activeGrid.getHeight()/2);
        grid.update(turtle.getActiveTurtles());
        return distanceTraveled;
    }
}
