package commands.information;

import java.util.Collection;
import java.util.List;

import turtle.Turtle;
import View.Grid;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw In order for all the basic
 *         commands to work, all grid containers must extend from this base
 *         class. Should contain all grids.
 */
public abstract class BaseGridContainer implements IInformationContainer {

	/**
	 * Tells the GUI to perform a specific function which takes no parameters
	 * based on the string provided.
	 * 
	 * @param guiCommand
	 *            String to specify GUI function
	 */
	public abstract void updateDisplayOptions(String guiCommand);

	/**
	 * Tells the GUI to perform a specific function which takes numbers as
	 * parameters based on the string provided.
	 * 
	 * @param guiCommand
	 *            guiCommand String to specify GUI function
	 * @param parameters
	 *            parameters required for the GUI function.
	 */
	public abstract void updateDisplayOptions(String guiCommand,
			List<Double> parameters);

	/**
	 * Adds a grid to the container.
	 * 
	 * @param grid
	 *            Grid to add
	 * @param isActive
	 *            Whether grid should be added as active or not
	 */
	public abstract void addGrid(Grid grid, boolean isActive);

	/**
	 * Sets a specific grid as active.
	 * @param gridID ID of grid to set active.
	 */
	public abstract void setGridAsActive(int gridID);

	/**
	 * Removes a specific grid from the grid list.
	 * @param gridID ID of grid to remove 
	 */
	public abstract void removeGrid(int gridID);

	
	/**
	 * Gets all active grids.
	 * @return All active grids.
	 */
	public abstract Collection<Grid> getActiveGrids();

	/**
	 * Tells grid(s) to update its turtles. 
	 * @param turtles Turtles to update.
	 */
	public abstract void update(Collection<Turtle> turtles);
}
