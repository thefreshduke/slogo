package commands.information;

import java.util.Collection;
import java.util.List;

import turtle.Turtle;
import View.Grid;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 * In order for all the basic commands to work, all grid containers must extend from this base class. 
 */
public abstract class BaseGridContainer implements IInformationContainer {

	
    /**
     * @param guiCommand
     */
    public abstract void updateDisplayOptions (String guiCommand);

    public abstract void updateDisplayOptions (String guiCommand, List<Double> parameters);

    public abstract void addGrid (Grid grid, boolean isActive);

    public abstract void setGridAsActive (int gridID);

    public abstract void removeGrid (int gridID);

    public abstract Collection<Grid> getActiveGrids ();

    public abstract void update (Collection<Turtle> turtles);
}
