package commands.viewCommands;

import java.util.ArrayList;
import java.util.List;

import View.Grid;
import backendExceptions.BackendException;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class HomeCommand extends TurtleCommand {

    private static final String EXACTLY_ONE_ACTIVE_GRID_IS_NOT_SET = "Exactly one active grid is not set";
	private static final String CLEAR_GUI = "clearGUI";

    public HomeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(CLEAR_GUI);
        BaseTurtleContainer turtle = getTurtleContainer();
        List<Grid> gridList = (ArrayList<Grid>) grid.getActiveGrids();
        if (gridList.size() != 1) {
        	throw new BackendException(null, EXACTLY_ONE_ACTIVE_GRID_IS_NOT_SET);
        }
        double distanceTraveled = turtle.setPosition(0, 0);
        return distanceTraveled;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
