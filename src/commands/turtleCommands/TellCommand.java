package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import View.Grid;
import backendExceptions.BackendException;

public class TellCommand extends MultipleTurtleCommand {

    public TellCommand(String userInput, boolean isExpression)
            throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute() throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        List<Integer> myAllTurtlesID = new ArrayList<>(turtle.getAllTurtlesByID());

        BaseGridContainer grid = getGridContainer();
        List<Grid> allGrids = (List<Grid>)grid.getActiveGrids();
        if (grid.getActiveGrids().size() != 1) {
            throw new BackendException(null, "More than one grid is active");
        }
        Grid activeGrid = allGrids.get(0);



        int maxID = findMax(getFutureActiveTurtleIDs());

        for (int i = 0; i <= maxID; i++) {
            if (!myAllTurtlesID.contains(i)) {
                Turtle newTurtle = activeGrid.addTurtle();
                turtle.addTurtle(newTurtle, false);
            }
        }
        turtle.setActiveTurtles(getFutureActiveTurtleIDs());
        double result = getInternalCommand().execute();

        return result;
    }

}
