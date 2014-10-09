package View;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;

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
import javafx.scene.shape.Line;
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
	private Stack<Point> myPoints=new Stack<Point>();
	//used to display Turtles most recent stats
	private Text lastX, lastY, lastOrientation;
	//for displaying command history
	private VBox commandHistoryBox;
	private String penColor;
	ResourceBundle myResources;
	private Stage myStage;
	public final static Dimension DEFAULT_SIZE=new Dimension(800,600);
	private static final int MAX_COMMAND_HISTORY = 5;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/Buttons";
	public SlogoView(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myGrid=new Grid(DEFAULT_SIZE.height-100, DEFAULT_SIZE.width-200, this.build(5) //ADD TURTLE*
				);
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
		myStage=mainStage;
		BorderPane mainLayout=new BorderPane();
		mainLayout.setPrefSize(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		mainLayout.setTop(addMenuBar());
		
		mainLayout.setLeft(setTextArea());
		mainLayout.setCenter(myGrid);
		mainLayout.setBottom(addButtons());
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
//		also have access to private variable pen color to determine what color the line should be
		if(penIsDown){
			myGrid.drawLine(myPoints.peek().x, myPoints.peek().y, x, y, penColor);
		}
	}

	/**
	 * Displays the error message "message" on the screen
	 * 
	 */
	private void showError(String message){}

	


	/**
	 * Moves both the Turtle and the Pen
	 * @param x			x location on a Grid
	 * @param y			y location on a Grid
	 * 
	 */
	public void move(int x, int y){
		myGrid.moveTurtle(x, y);
		drawLine(x, y);
	}


	/**
	 * Takes in the coordinates (x,y) from the controller and pops the last coordinate from the stack to call move(int x, int y)
	 * @param x		x location on the Grid
	 * @param y		y location on the Grid
	 */
	private void update(int x, int y){
		move(x, y);
		drawLine(x, y);
		myPoints.push(new Point(x, y));
	}
		
	
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
		Point lastMove=myPoints.pop();
		myGrid.undo(myPoints.peek().x, myPoints.peek().y);
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
		Pane myTextArea=new Pane();
		myTextArea.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myTextArea.setPrefSize(200, DEFAULT_SIZE.height-200);
		
//		create command line
		Label label = new Label("Commands:");
		label.setTextFill(Color.WHITE);
		label.setStyle("-fx-font-size: 25");
		label.setPrefSize(200, 50);
		label.relocate(10, 5);
		commandLine = new TextField();
		commandLine.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) sendCommand();
			}
		});
		commandLine.relocate(5, 60);
		commandLine.setPrefSize(190,100);
		ButtonTemplate enter = new ButtonTemplate(myResources.getString("enter"),0,0, event-> this.sendCommand());
		enter.relocate(50, 180);
		
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
		lastX.relocate(5, 250);
		lastY.relocate(5, 280);
		lastOrientation.relocate(5, 310);
		
//		temporary background color chooser
		MenuBar mBar = new MenuBar();
		MenuTemplate m = new MenuTemplate("Background Color");
		m.addMenuItem("RED", event -> setBackgroundColor("RED"));
		m.addMenuItem("BLUE", event -> setBackgroundColor("BLUE"));
		m.addMenuItem("GREEN", event -> setBackgroundColor("GREEN"));
		m.addMenuItem("WHITE", event -> setBackgroundColor("WHITE"));
		m.addMenuItem("BLACK", event -> setBackgroundColor("BLACK"));
		mBar.getMenus().add(m);
		mBar.setPrefSize(150, 25);
		mBar.relocate(25, 350);
	

//		temporary pen color chooser
		MenuBar pBar = new MenuBar();
		MenuTemplate p = new MenuTemplate("Pen Color");
		p.addMenuItem("RED", event -> setPenColor("RED"));
		p.addMenuItem("BLUE", event -> setPenColor("BLUE"));
		p.addMenuItem("GREEN", event -> setPenColor("GREEN"));
		p.addMenuItem("WHITE", event -> setPenColor("WHITE"));
		p.addMenuItem("BLACK", event -> setPenColor("BLACK"));
		pBar.getMenus().add(p);
		pBar.relocate(25, 385);
		pBar.setPrefSize(150, 25);
//		command History
		
		commandHistoryBox = new VBox();
		Text history = new Text("  Command History");
		history.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		history.setFill(Color.WHITE);
		history.relocate(0, 420);
		commandHistoryBox.setSpacing(10);		
		updateCommandHistory();
		commandHistoryBox.relocate(0, 450);
		myTextArea.getChildren().addAll(label, commandLine, enter, lastX, lastY, lastOrientation,
				mBar, pBar, history, commandHistoryBox);
		return myTextArea;
	}
	
	
	private Pane addButtons(){
		int x=100;
		int y=10;
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		
		ButtonTemplate uploadImage=new ButtonTemplate(myResources.getString("uploadImage"), x, y, event->myModel.uploadTurtleImage(), 150, 55);
		
		ButtonTemplate backgroundImage=new ButtonTemplate("Upload Background\n\t  Image", x+=160,y, event->
						myGrid.uploadMyBackgroundImage(myStage), 150, 55);
	
		ButtonTemplate clear=new ButtonTemplate(myResources.getString("clear"),x+=160, y, event->myGrid.clear());
		
		ButtonTemplate undo=new ButtonTemplate(myResources.getString("undo"),x+=85, y, event->this.undo());
		
		ButtonTemplate penDown=new ButtonTemplate(myResources.getString("penDown"),x+=85, y, event-> setPenDown(true));

		ButtonTemplate penUp=new ButtonTemplate(myResources.getString("penUp"),x+=85, y, event-> setPenDown(false));

		ButtonTemplate refGrid=new ButtonTemplate(myResources.getString("toggleReferenceGrid"),x+=85, y, event->toggleRefGrid(), 150, 55);

		myButtonPanel.getChildren().addAll(uploadImage, clear, undo, penDown, penUp, refGrid, backgroundImage);
		
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
		commandHistoryBox.getChildren().clear();
		
		if(myCommands.size() > MAX_COMMAND_HISTORY){
			myCommands.poll();
		}
		for(ButtonTemplate b : myCommands){
			commandHistoryBox.getChildren().add(b);
		}
	}
	
	public void setBackgroundColor(String color){
		myGrid.setBackgroundColor(color);
	}
	
	public void setPenColor(String color){
		penColor = color;
	}
	
	
}
