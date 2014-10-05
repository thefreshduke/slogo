import java.awt.Dimension;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SlogoView {
	
	//Default Dimension
	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	//an ArrayList of all the working commands given by the user
	public ArrayList<String> myCommands=new ArrayList<String>();

	public SlogoView(){}

	/**
	 * Clears everything in the mainSimulationPanel (can be called by the controller when the command clear is given)
	 */
	public void clear(){}


	/**
	 * Creates a layout of the GUI and adds the objects to the Stage
	 * @param mainStage   the Stage for the GUI to operate on 
	 */
	public void initialize(Stage mainStage){}

	/**
	 * Displays an error message on the Stage
	 * @param message		Represents the error message to be displayed
	 */
	public void showError(String message){}

	/**
	 * This function is called by the controller in order to move a Turtle and the Line of the Turtle
	 * @param x				Represents an x location on the Grid
	 * @param y				Represents a y location on the Grid
	 * @param orientation	Represents the orienation of the Turtle
	 */
	public void update(int x, int y, double orientation){}

	
	/**
	 * Changes the Turtle's visibility (called by controller)
	 * @param b		Representing a boolean that determines whether or not
	 * 				the turtle should be visible
	 */
	public void setTurtleVisible(boolean b){} 
	
	
	/**
	 * Changes the line's visibility (called by controller if commands Pen Up or Pen Down are called)
	 * @param b		Representing a boolean that determines whether or not 
	 * 				the turtle should be visible
	 */
	public void setLineVisible(boolean b){}
	
	/**
	 * 
	 * @return 	A String representing the lanugage the user prefers
	 */
	public String getLanguage(){
		return null;
	}

	/**
	 * Gives the controller the command to parse and interpret
	 * @param command		String representing the command the user inputs	
	 */
	private void sendCommand(String command){}
}
