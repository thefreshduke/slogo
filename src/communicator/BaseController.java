package communicator;
import turtle.Turtle;
import View.Grid;
import View.SlogoView;

import java.io.File;
import java.util.List;

import backendExceptions.BackendException;
import commands.information.IInformationContainer;
import commands.information.BaseVariableContainer;
import javafx.scene.image.Image;

public abstract class BaseController {

	public BaseController(SlogoView view){
	}

	/**
	 * Receive commands from from View via string.
	 * @param enteredText Text input by user
	 */
	public abstract void receiveCommand(String enteredText);

	/**
	 * Initializes the model.
	 * @param initialTurtlePosition
	 * @param initialOrientation
	 */
	protected abstract void initializeModel();

	/**
	 * Pauses the turtle. Changes what coordinates are sent to front end.
	 * @param stoppedPosition The stopped position on the front end. Takes into account lag.
	 */
	public abstract void pause(int x, int y);

	/**
	 * Hard stops the turtle. Erases all coordinates and commands that it was supposed to do.
	 */
	public abstract void stop();    

	/**
	 * If any errors happen on the back end, it should not crash the program
	 * Sends the error back to View so that it can tell the user of the problem
	 * @param ex exception that was thrown in back end
	 */
	protected abstract void reportErrorToView(Exception ex);

	/**
	 * Hard sets the Turtle's position and orientation. It will clear any queued commands and coordinates.
	 * @param position New position
	 * @param orientation New orientation
	 */
	public abstract void hardSetTurtle(double x, double y, double orientationAngle);

	/**
	 * Hard sets just the position. Still clears any queued commands and coordinates
	 * @param position New position
	 */

	public abstract void start();


	/**
	 * Hard sets just the orientation. Still clears any queued commands and coordinates
	 * @param orientation New orientation
	 */
	public void hardSetTurtleOrientation(double orientation){}

	/**
	 * Gives the controller the language file resource selected by the user
	 * @param file File of language resource to be used
	 */
	public abstract void loadLanguage(File file);
	
	public abstract Turtle getFirstTurtle();

	public abstract void addTurtle(Turtle turtle, int ID, boolean isActive);
	
	public abstract void addGrid(Grid grid, boolean isActive);

	public abstract List<Turtle> getActiveTurtles();
	
	public abstract IInformationContainer loadPreferences(IInformationContainer container, File file) throws BackendException;
	
	public abstract void savePreferences(IInformationContainer container, String filename) throws BackendException;

	//public abstract void setTurtleImage(Image image);

}


