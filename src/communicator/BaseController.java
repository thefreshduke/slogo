package communicator;

import java.io.File;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import View.SlogoView;
import backendExceptions.BackendException;

import commands.information.IInformationContainer;

/**
 * Abstract class for the Controller that provides the point-of-contact between the 
 * FrontEnd and BackEnd. 
 * @author Duke
 *
 */
public abstract class BaseController {

	public BaseController (SlogoView view) {
	}

	/**
	 * Receive commands from from View via string.
	 * 
	 * @param enteredText
	 *            Text input by user
	 */
	public abstract void receiveCommand (String enteredText);

	/**
	 * Pauses the turtle. Changes what coordinates are sent to front end.
	 * 
	 * @param stoppedPosition
	 *            The stopped position on the front end. Takes into account lag.
	 */
	public abstract void pause (int x, int y);

	/**
	 * Hard stops the turtle. Erases all coordinates and commands that it was
	 * supposed to do.
	 */
	public abstract void stop ();

	/**
	 * If any errors happen on the back end, it should not crash the program
	 * Sends the error back to View so that it can tell the user of the problem
	 * 
	 * @param ex
	 *            exception that was thrown in back end
	 */
	protected abstract void reportErrorToView (Exception ex);

	/**
	 * Hard sets just the position. Still clears any queued commands and
	 * coordinates
	 * 
	 * @param position
	 *            New position
	 */

	public abstract void start ();

	/**
	 * Gives the controller the language file resource selected by the user
	 * 
	 * @param file
	 *            File of language resource to be used
	 */
	public abstract void loadLanguage (File file);
	
	/**
	 * 
	 * @param turtle to be added
	 * @param gridID - grid on which turtle to be added
	 * @param isActive - whether the added turtle is active or not via the view
	 */

	public abstract void addTurtle (Turtle turtle, int gridID, boolean isActive);
	
	/**
	 * 
	 * @param grid to be added
	 * @param isActive - whether the grid is active
	 */
	public abstract void addGrid (Grid grid, boolean isActive);

	/**
	 * 
	 * @return list of active turtles that can be obtained
	 */
	public abstract List<Turtle> getActiveTurtles ();

	/**
	 * 
	 * @param file from which preferences are loaded
	 * @return container with loaded information
	 * @throws BackendException if failure to load file
	 */
	public abstract IInformationContainer loadPreferences (File file) throws BackendException;

	/**
	 * 
	 * @param container that should be saved
	 * @param filename to which container should be saved
	 * @throws BackendException if failure to save properly
	 */
	public abstract void savePreferences (IInformationContainer container, String filename)
			throws BackendException;

	/**
	 * 
	 * @param gridID that should be set active
	 */
	public abstract void setGridAsActive (int gridID);
	
	/**
	 * 
	 * @param turtle that should be set active
	 */
	public abstract void setTurtleAsActive(int turtleID);

}
