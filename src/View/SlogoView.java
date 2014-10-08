package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.ResourceBundle;

import communicator.BaseController;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SlogoView {

	//list of all the objects on the GUI that the user can interact with
	private ArrayList<UserObjects> userInteractions=new ArrayList<UserObjects>();
	private Grid myGrid;
	private BaseController myController;
	//a Group for all the components of the GUI to be added to
	private Group root=new Group();
	//an ArrayList of all the working commands given by the user
	public ArrayList<String> myCommands=new ArrayList<String>();
	private Scene myScene;
	private SlogoViewModel myModel;
	//flag for if pen is up or down
	private boolean penIsDown;
	ResourceBundle myResources;
	public final static Dimension DEFAULT_SIZE=new Dimension(1000,800);
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/Buttons";
	public SlogoView(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myGrid=new Grid(DEFAULT_SIZE.height-100, DEFAULT_SIZE.width-200, this.build(5));
		myModel=new SlogoViewModel(myController, this);
	}	
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
	//public void clear()

	/**
	 * 
	 * @return A KeyFrame for the TimeLine of the program
	 */
	private KeyFrame build(int fps){
		Duration speed=Duration.millis(1000/fps);
		final EventHandler<ActionEvent> loop=new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent evt){
				//update(my);
			}
		};
		return new KeyFrame(speed, loop);
	}

	/**
	 * Creates a layout of the GUI and adds the objects to the Stage
	 * @param mainStage   the Stage for the GUI to operate on 
	 */
	
	
	public void initialize(Stage mainStage){
		BorderPane mainLayout=new BorderPane();
		mainLayout.setPrefSize(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		mainLayout.setTop(addMenuBar());
		mainLayout.setBottom(addButtons());
		mainLayout.setLeft(setTextArea());
		mainLayout.setCenter(myGrid);
		root.getChildren().add(mainLayout);
		myScene=new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		mainStage.setScene(myScene);
	}

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
	public void undo(){

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
	private MenuBar addMenuBar(){
		MenuBar myMenu=new MenuBar();
		//myMenu.setStyle("-fx-background-color:#000080");
		myMenu.setStyle( "-fx-border-width: 5");
		myMenu.setPrefSize(DEFAULT_SIZE.width, 30);
		return myMenu;
	}
	private Pane setTextArea(){
		VBox myTextArea=new VBox();
		myTextArea.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myTextArea.setPrefSize(200, DEFAULT_SIZE.height-200);
		return myTextArea;
	}
	private Pane addButtons(){
		int x=0;
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		
		ButtonTemplate draw=new ButtonTemplate(myResources.getString("uploadImage"), x, 0, event->myModel.uploadTurtleImage());
	
		ButtonTemplate undo=new ButtonTemplate(myResources.getString("undo"),x+=110, 0, event->myModel.undo());
		
		myButtonPanel.getChildren().addAll(draw, undo);
		return myButtonPanel;
	}
	public void home(){
		
	}
	public void setPenDown(boolean b){
		penIsDown = b;
	}
}
