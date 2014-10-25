import javafx.scene.Node;

/**
 * The controller which acts as the gateway between the View and the Model. All
 * communication between the two will go through the controller. The controller
 * will send to the controller text commands that the end user enters. After
 * parsing by the parser, the controller will either send the command back to
 * the view or the model, depending on what kind of command it is. If a command
 * changes the turtle's state which needs to be visualized, that information
 * will be sent to the view from the model via the controller (i.e. new
 * location, orientation).
 * 
 * @author Duke
 *
 */
public abstract class BaseController {

    public BaseController (Node view) {
    }

    /**
     * Receive commands from from View via string.
     * 
     * @param enteredText
     *            Text input by user
     */
    public abstract void receiveCommand (String enteredText);

    /**
     * Initializes the model.
     * 
     * @param initialTurtlePosition
     * @param initialOrientation
     */
    protected abstract void initializeModel (int turtleX, int turtleY, double turtleOrientation);

    /**
     * Send resulting turtle coordinates to view
     */
    protected void sendTurtleResultsToView () {

    }

    /**
     * Does the next queued command for turtle
     */
    protected abstract void doNextTurtleCommand ();

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
    protected void reportErrorToView (Exception ex) {
    }

    /**
     * Hard sets the Turtle's position and orientation. It will clear any queued
     * commands and coordinates.
     * 
     * @param position
     *            New position
     * @param orientation
     *            New orientation
     */
    public abstract void hardSetTurtle (double x, double y, double orientationAngle);

    /**
     * Hard sets just the position. Still clears any queued commands and
     * coordinates
     * 
     * @param position
     *            New position
     */
    public void hardSetTurtlePosition (double x, double y) {
    }

    /**
     * Hard sets just the orientation. Still clears any queued commands and
     * coordinates
     * 
     * @param orientation
     *            New orientation
     */
    public void hardSetTurtleOrientation (double orientation) {
    }
}
