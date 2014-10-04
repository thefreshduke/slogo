package View;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SlogoView {

	//list of all the objects on the GUI that the user can interact with
	private ArrayList<UserObjects> userInteractions=new ArrayList<UserObjects>();
	//a Group for all the components of the GUI to be added to
	private Group root=new Group();
	//an ArrayList of all the working commands given by the user
	public ArrayList<String> myCommands=new ArrayList<String>();

	public SlogoView(){}

	/**
	 * Makes a Button that is to be added to the GUI's Stage
	 * Adds the Button to the userInteractions List
	 * 
	 * @param s				A String representing the label of the button
	 * @param handler		An Event for the button to react on
	 * @return				a Button for display on the GUI
	 */
	private UserObjects makeUserObject(String s, EventHandler<ActionEvent> handler){
		return null;

	}

	/**
	 * Clears everything in the mainSimulationPanel (can be called by the controller when the command clear is given)
	 */
	public void clear(){}


	/**
	 * 
	 * @return A KeyFrame for the TimeLine of the program
	 */
	private KeyFrame build(){
		return null;}

	/**
	 * Creates a layout of the GUI and adds the objects to the Stage
	 * @param mainStage   the Stage for the GUI to operate on 
	 */
	public void initialize(Stage mainStage){}

	/**
	 * Draws a line from its location to (x,y)
	 * @param x			x location 
	 * @param y			y location
	 * 
	 */
	private void drawLine(int x, int y){}

	/**
	 * Displays the error message "message" on the screen
	 * 
	 */
	private void showError(String message){}

	/**
	 * Moves the Turtle from its current location to (x, y)
	 * @param x     x location on a Grid
	 * @param y		y location on a Grid
	 */
	public void moveTurtle(int x, int y){}


	/**
	 * Moves both the Turtle and the Pen
	 * @param x			x location on a Grid
	 * @param y			y location on a Grid
	 * 
	 */
	public void move(int x, int y){

	}


	/**
	 * Takes in the coordinates (x,y) from the controller and pops the last coordinate from the stack to call move(int x, int y)
	 * @param x		x location on the Grid
	 * @param y		y location on the Grid
	 */
	private void update(int x, int y){}

	
	/**
	 * Changes the Turtle's visibility (called by controller)
	 * @param b		Representing a boolean that determines whether or not
	 * 				the turtle should be visible
	 */
	public void setTurtleVisible(boolean b){} 
	
	
	/**
	 * Displays the Turtle and the Line at it's position before the last command
	 */
	private void undo(){

	}
	/**
	 * Makes Buttons for the top 5 recent commands and displays them on the GUI
	 */
	private void displayAndMakeRecentCommands(){}


	/**
	 *Receives x and y location from the controller to move only the Turtle
	 * @param x		x location on Grid
	 * @param y		y location on Grid
	 */
	public void updateTurtle(int x, int y){}



	/**
	 *Passes the commands from the textbox on the GUI to the controller for parsing.  
	 * @param s		String representing the command to send that was inputed by the user
	 *  
	 */
	private void sendCommand(String s){}



}
