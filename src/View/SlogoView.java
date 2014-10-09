package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import communicator.BaseController;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
	public Queue<ButtonTemplate> myCommands=new LinkedList<ButtonTemplate>();
	private Scene myScene;
	private SlogoViewModel myModel;
	//flag for if pen is up or down, flag for if ref grid is visible
	private boolean penIsDown, refGridOn;
	private TextField commandLine;
	//used to display Turtles most recent stats
	private Text lastX, lastY, lastOrientation;
	//for displaying command history
	private VBox commandHistory;
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
	private void drawLine(int x, int y){
		
//		check if pen is down before drawing
		if(penIsDown){
			
			
		}
		
		
	}

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
	public void moveTurtle(int x, int y){
		
		
//		update turtles most current stats for display
		updateTurtleStats(x, y, 0);
	}


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
	private void sendCommand(){
//		myController.receiveCommand(commandLine.getText());
		
		ButtonTemplate mostRecent = new ButtonTemplate(commandLine.getText(), 0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();
		
		updateCommandHistory();
	}
	
//	ugly workaround, need to find elegant solution to get rid of repeated code
	private void sendButtonCommand(ButtonTemplate b){
//		myController.receiveCommand(commandLine.getText());
		ButtonTemplate mostRecent = new ButtonTemplate(b.getText(), 0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();
		
		updateCommandHistory();

	}
	

	
	private MenuBar addMenuBar(){
		MenuBar myMenu=new MenuBar();
		//myMenu.setStyle("-fx-background-color:#000080");
		myMenu.setStyle( "-fx-border-width: 5");
		myMenu.setPrefSize(DEFAULT_SIZE.width, 30);
		MenuTemplate fileMenu = new MenuTemplate("File");
		MenuTemplate languages = new MenuTemplate("Languages");
		MenuTemplate help = new MenuTemplate("Help");
		this.createMenuItemsUnderFile(fileMenu);
		this.createMenuItemsUnderLanguages(languages);
		this.createMenuItemsUnderHelp(help);
		myMenu.getMenus().addAll(fileMenu, languages, help);
		return myMenu;
	}
	
	

	public void createMenuItemsUnderFile(MenuTemplate fileMenu){
		
		fileMenu.addMenuItem("Export to XML", null);
		fileMenu.addMenuItem("Import to XML", null);
	}
	
	public void createMenuItemsUnderLanguages(MenuTemplate languages){
		
		languages.addMenuItem("English", event -> myModel.loadLanguageResource("English"));
		languages.addMenuItem("French", event -> myModel.loadLanguageResource("French"));
		languages.addMenuItem("Portuguese", event -> myModel.loadLanguageResource("Portuguese"));
		languages.addMenuItem("Italian", event -> myModel.loadLanguageResource("Italian"));
		languages.addMenuItem("Chinese", event -> myModel.loadLanguageResource("Chinese"));
		languages.addMenuItem("Russian", event -> myModel.loadLanguageResource("Russian"));

	}
	
	public void createMenuItemsUnderHelp(MenuTemplate help){	
		
		help.addMenuItem("Help Page", event -> myModel.helpPage());
	}
	
	
	private Pane setTextArea(){
		VBox myTextArea=new VBox();
		myTextArea.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myTextArea.setPrefSize(200, DEFAULT_SIZE.height-200);
		
//		create command line
		Label label = new Label("Commands:");
		label.setTextFill(Color.WHITE);
		commandLine = new TextField();
		
		commandLine.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) sendCommand();
			}
		});
		
		ButtonTemplate enter = new ButtonTemplate(myResources.getString("enter"),0,0, event-> this.sendCommand());
		myTextArea.setSpacing(10);	
		
//		create Turtle display stats
		lastX = new Text("X Position: " + 0);
		lastY = new Text("Y Position: " + 0);
		lastOrientation = new Text("Orientation: " + 0);
		lastX.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		lastY.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		lastOrientation.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		lastX.setFill(Color.WHITE);
		lastY.setFill(Color.WHITE);
		lastOrientation.setFill(Color.WHITE);
		
//		temporary background color chooser
		MenuBar bar = new MenuBar();
		MenuTemplate m = new MenuTemplate("Background Color");
		bar.getMenus().add(m);
		
		m.addMenuItem("RED", event -> setBackgroundColor("RED"));
		m.addMenuItem("BLUE", event -> setBackgroundColor("BLUE"));
		m.addMenuItem("GREEN", event -> setBackgroundColor("GREEN"));
		m.addMenuItem("WHITE", event -> setBackgroundColor("WHITE"));
		m.addMenuItem("BLACK", event -> setBackgroundColor("BLACK"));

//		command History
		
		commandHistory = new VBox();
		Text history = new Text("Command History");
		history.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		history.setFill(Color.WHITE);
		commandHistory.setSpacing(10);		
		updateCommandHistory();
		
		myTextArea.getChildren().addAll(label, commandLine, enter, lastX, lastY, lastOrientation, bar,history, commandHistory);
		return myTextArea;
	}
	private Pane addButtons(){
		int x=0;
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		
		ButtonTemplate uploadImage=new ButtonTemplate(myResources.getString("uploadImage"), x, 0, event->myModel.uploadTurtleImage(), 150, 75);
	
		ButtonTemplate clear=new ButtonTemplate(myResources.getString("clear"),x+=200, 0, event->myModel.clear());
		
		ButtonTemplate undo=new ButtonTemplate(myResources.getString("undo"),x+=110, 0, event->myModel.undo());
		
		ButtonTemplate penDown=new ButtonTemplate(myResources.getString("penDown"),x+=110, 0, event->myModel.penDown());

		ButtonTemplate penUp=new ButtonTemplate(myResources.getString("penUp"),x+=110, 0, event->myModel.penUp());

		ButtonTemplate refGrid=new ButtonTemplate(myResources.getString("toggleReferenceGrid"),x+=110, 0, event->toggleRefGrid());

		myButtonPanel.getChildren().addAll(uploadImage, clear, undo, penDown, penUp, refGrid);
		
		return myButtonPanel;
	}
	public void home(){
		
	}
	public void setPenDown(boolean b){
		penIsDown = b;
	}
	
	public void toggleRefGrid(){
		this.refGridOn = !refGridOn;
		myGrid.toggleRefGrid(refGridOn);
		
	}
	
	public void updateTurtleStats(int x, int y, int or){
		lastX.setText("X Position: " + x);
		lastY.setText("Y Position: " + y);
		lastOrientation.setText("Orientation " + or);
	}
	
	public void updateCommandHistory(){
		if(myCommands.size() > 5){
			myCommands.poll();
		}
		commandHistory.getChildren().clear();
		for(ButtonTemplate b : myCommands){
			commandHistory.getChildren().add(b);
		}
	}
	
	public void setBackgroundColor(String color){
		myGrid.setBackgroundColor(color);
	}
}
