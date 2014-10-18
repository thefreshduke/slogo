package View;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.swing.JOptionPane;

import turtle.Turtle;
import communicator.MainController;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SlogoView {
	HashMap<String, GUIFunction> myButtonMap=new HashMap<String, GUIFunction>();
	private Grid myGrid;
	private MainController myController;
	//a Group for all the components of the GUI to be added to
	private Group root=new Group();
	//an ArrayList of all the working commands given by the user
	public Queue<ButtonTemplate> myCommands=new LinkedList<ButtonTemplate>();
	private Scene myScene;
	private SlogoViewModel myModel;
	private TextField commandLine;
	//used to display Turtles most recent stats
	private Text lastX, lastY, lastOrientation;
	String penColor;
	//for displaying command history
	private VBox commandHistoryBox;
	private MenuTemplate userCommands;
	ResourceBundle myResources;
	private Stage myStage;
	public final static Dimension DEFAULT_SIZE=new Dimension(1000,600);
	private static final int MAX_COMMAND_HISTORY = 5;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/Buttons";
	public SlogoView(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myController=new MainController(this);
		myGrid=new Grid(DEFAULT_SIZE.height-100, DEFAULT_SIZE.width-200, this.build(5), myController.getTurtle());
		myModel=new SlogoViewModel(myController);
	}	


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
		myGrid.addTurtle(myController.getTurtle());
	}
	


	/**
	 * Displays the error message "message" on the screen
	 * 
	 */
	private void showError(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	

	



	/**
	 *Passes the commands from the textbox on the GUI to the controller for parsing.  
	 * @param s		String representing the command to send that was inputed by the user
	 *  
	 */
	private void sendCommand(){

		myController.receiveCommand(commandLine.getText());
		ButtonTemplate mostRecent = new ButtonTemplate(commandLine.getText(), 0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();

		updateCommandHistory();
	}

	//	ugly workaround, need to find elegant solution to get rid of repeated code
	private void sendButtonCommand(ButtonTemplate b){
		myController.receiveCommand(commandLine.getText());
		ButtonTemplate mostRecent = new ButtonTemplate(b.getText(), 0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();

		updateCommandHistory();

	}



	private MenuBar addMenuBar(){
		MenuBar myMenu=new MenuBar();
		myMenu.setStyle( "-fx-border-width: 5");
		myMenu.setPrefSize(DEFAULT_SIZE.width, 30);
		MenuTemplate fileMenu = new MenuTemplate("File");
		MenuTemplate languages = new MenuTemplate("Languages");
		MenuTemplate help = new MenuTemplate("Help");
		userCommands = new MenuTemplate("User Commands");
		this.createMenuItemsUnderFile(fileMenu);
		this.createMenuItemsUnderLanguages(languages);
		this.createMenuItemsUnderHelp(help);
		myMenu.getMenus().addAll(fileMenu, languages, userCommands, help);
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
		String[] value=myResources.getString("enter").split(";");
		Button enter = new Button (value[0]);
		enter.relocate(Double.parseDouble(value[1]), Double.parseDouble(value[2]));
		enter.setOnAction(event_->this.sendCommand());
		value=myResources.getString("makeCommand").split(";");
		Button makeCommand = new Button(value[0]);
		makeCommand.setOnAction(event-> makeUserCommand(commandLine.getText()));
		makeCommand.setPrefSize(110, 55);
		makeCommand.relocate(Double.parseDouble(value[1]),Double.parseDouble(value[2]));



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
		myTextArea.getChildren().addAll(label, commandLine, enter, makeCommand, lastX, lastY, lastOrientation,
				mBar, pBar, history, commandHistoryBox);
		return myTextArea;
	}


	private Pane addButtons(){
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myButtonPanel.getChildren().addAll(makeBottomButtons());

		return myButtonPanel;
	}
	public void home(){

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

	public void makeUserCommand(String command){
		commandLine.clear();
		String name = JOptionPane.showInputDialog("Give a Name for your Command");
		userCommands.addMenuItem(name, event->executeUserCommand(command));
	}

	public void executeUserCommand(String command){
		myController.receiveCommand(commandLine.getText());
		commandLine.setText(command);
		ButtonTemplate mostRecent = new ButtonTemplate(commandLine.getText(),
				0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();
		updateCommandHistory();
	}
	
	public ArrayList<ButtonTemplate> makeBottomButtons(){
		makeMap();
		ArrayList<ButtonTemplate> myButtons=new ArrayList<ButtonTemplate>();
		for (String s: myButtonMap.keySet()){
			String[] value=myResources.getString(s).split(";");
			if (!(s.equals("makeCommand")||s.equals("enter")))
					myButtons.add(new ButtonTemplate(value[0], Double.parseDouble(value[1]), Double.parseDouble(value[2]), myButtonMap.get(s)));
		}
		return myButtons;
	}
	public void makeMap(){
		myButtonMap.put("penDown", new SetPenDown(myGrid.getActivePens()));
		myButtonMap.put("undo", new Undo(myGrid));
		myButtonMap.put("backgroundImage", new SetBackgroundImage(myGrid, myStage));
		myButtonMap.put("clear", new ClearFunction(myGrid));
		myButtonMap.put("penUp", new SetPenUp(myGrid.getActivePens()));
		myButtonMap.put("toggleReferenceGrid", new ToggleGridLines(myGrid, 50));
		myButtonMap.put("uploadImage", new TurtleImageChange(myGrid.getActiveTurtles(), myStage));
		
	}
	public Grid getGrid() {
		return myGrid;
	}

}
