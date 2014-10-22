package View;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.swing.JOptionPane;

import GUIFunctions.AddTurtle;
import GUIFunctions.ClearFunction;
import GUIFunctions.SetBackgroundImage;
import GUIFunctions.SetPenDown;
import GUIFunctions.SetPenUp;
import GUIFunctions.Undo;
import turtle.Turtle;
import communicator.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	private HashMap<String, GUIFunction> myButtonMap=new HashMap<String, GUIFunction>();
	private SingleGrid myGrid;
	private MainController myController;
	private TabsOfGrids myGridTabs=new TabsOfGrids();
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
		myGrid=new SingleGrid(DEFAULT_SIZE.height-150, DEFAULT_SIZE.width-200, this.build(5));
		myController=new MainController(this);
		myModel=new SlogoViewModel(myController);
		myController.gridReady();
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
				setUpKeyBoardHandler(myStage);
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
		mainLayout.setCenter(myGrid);
		mainLayout.setLeft(setTextArea());
		mainLayout.setBottom(addButtons());
		root.getChildren().add(mainLayout);
		root.getChildren().add(myGridTabs);
		myScene=new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		myStage.setScene(myScene);
		myGrid.addTurtle(myController.getFirstTurtle());
		myGridTabs.addTab("GRID 1", myGrid);
		setGridTabsFocused(myGridTabs);
		setGridTabsFocused(commandLine);
		//Timeline myTimeLine=new Timeline();
		//myTimeLine.setCycleCount(Timeline.INDEFINITE);
		//myTimeLine.getKeyFrames().add(this.build(40));
		//myTimeLine.play();
		this.setUpKeyBoardHandler(mainStage);

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
		if (commandLine.getText()!=null){
		myController.receiveCommand(commandLine.getText());
		ButtonTemplate mostRecent = new ButtonTemplate(commandLine.getText(), 0, 0, null, 180, 35);
		mostRecent.addEvent(event -> sendButtonCommand(mostRecent));
		myCommands.add(mostRecent);
		commandLine.clear();
		updateCommandHistory();
		}
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
		MenuTemplate personalize=new MenuTemplate("Personalize");
		userCommands = new MenuTemplate("User Commands");
		//this.makeLanguageMenuItems(languages);
		this.createMenuItemsUnderHelp(help);
		myMenu.getMenus().addAll(fileMenu, languages, userCommands, help, personalize);
		//this.createMenuItemsUnderPersonalize(personalize);
	
		return myMenu;
	}

	//public void createMenuItemsUnderPersonalize(MenutTemplate pMenu){
	//	pMenu.addMenuItem("")
	//}

	public void createMenuItemsUnderFile(MenuTemplate fileMenu){
		fileMenu.addMenuItem("Export to XML", null);
		fileMenu.addMenuItem("Import to XML", null);
		fileMenu.addMenuItem("Add Turtle", event-> addTurtle());
		fileMenu.addMenuItem("Add Grid", event->addGrid());
	}
	public void addTurtle(){
		//tell
		//getNewTurtle
		
	}
	public void addGrid(){
		SingleGrid anotherGrid=new SingleGrid(myGrid.myHeight, myGrid.myWidth, this.build(40));
		myGridTabs.addTab("Grid 2", anotherGrid);
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
		Button enable=new Button("Enable");
		enable.relocate(15, 5);
		commandLine = new TextField();
		EventHandler enterEvent=new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) 
					sendCommand();		
			}
		};
		enable.setOnAction(event->commandLine.setDisable(false));
		enable.setPrefSize(20,20);
		myGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, event->removeCommandLine());
		commandLine.addEventHandler(KeyEvent.KEY_PRESSED,enterEvent);
		String[] value=myResources.getString("enter").split(";");
		Button enter = new Button (value[0]);
		enter.relocate(Double.parseDouble(value[1]), Double.parseDouble(value[2]));
		System.out.println(commandLine.getText());
		enter.setOnAction(event->this.sendCommand());
		commandLine.relocate(5, 60);
		commandLine.setPrefSize(190,100);
		
		
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
		ColorSelection colorSelection = new ColorSelection(this);
		MenuBar mBar = colorSelection.getBackgroundColorMenuBar();
		mBar.relocate(25, 350);

		//		temporary pen color chooser
		MenuBar pBar = colorSelection.getPenColorMenuBar();
		pBar.relocate(25, 385);
		//		command History

		commandHistoryBox = new VBox();
		Text history = new Text("  Command History");
		history.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		history.setFill(Color.WHITE);
		history.relocate(0, 420);
		commandHistoryBox.setSpacing(10);		
		updateCommandHistory();
		commandHistoryBox.relocate(0, 450);
		myTextArea.getChildren().addAll(label, commandLine, enter, enable,makeCommand, lastX, lastY, lastOrientation,
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
		makeButtonMap();
		ArrayList<ButtonTemplate> myButtons=new ArrayList<ButtonTemplate>();
		for (String s: myButtonMap.keySet()){
			String[] value=myResources.getString(s).split(";");
			if (!(s.equals("makeCommand")||s.equals("enter")))
				myButtons.add(new ButtonTemplate(value[0], Double.parseDouble(value[1]), Double.parseDouble(value[2]), myButtonMap.get(s)));
		}
		return myButtons;
	}
	public void makeButtonMap(){
		myButtonMap.put("penDown", new SetPenDown(myGrid));
		myButtonMap.put("undo", new Undo(myGrid));
		myButtonMap.put("backgroundImage", new SetBackgroundImage(myGrid, myStage));
		myButtonMap.put("clear", new ClearFunction(myGrid));
		myButtonMap.put("penUp", new SetPenUp(myGrid));
		myButtonMap.put("toggleReferenceGrid", new ToggleGridLines(myGrid, 50));
		myButtonMap.put("uploadImage", new TurtleImageChange(myGrid, myStage));
		myButtonMap.put("addTurtle", new AddTurtle(myGrid));

	}

	
	public ArrayList<MenuItemTemplate> makeLanguageMenuItems(){
		ArrayList<MenuItemTemplate> items = new ArrayList<>();
		String[] languages = new String[] {"English", "Chinese", "French" , "Italian", "Portuguese", "Russian" };
		for(String s : languages){
			items.add(new MenuItemTemplate(s,
					event -> myController.loadLanguage(new File("/resources/languages" + s + ".properties"))));
		}
		return items;
	}
	
	public void makeMenuItemMap(){
	}
	

	public Grid getGrid() {
		return myGrid;
	}
	public void setGridTabsFocused(Node o){
		myGridTabs.focusedProperty().addListener(new ChangeListener <Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {  
				if (!newValue.booleanValue())
					o.setDisable(true);
				
				else
					o.setDisable(false);
				
		}});
		
	}
	public void setUpKeyBoardHandler(Stage main){
		EventHandler<KeyEvent> moveTurtle=new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				for (Turtle t: myGrid.getActiveTurtles()){
					t.move(e.getCode());
				}
				myGrid.keyUpdate();;
				
			}
		};

		//System.out.println(moveTurtle);
		myGridTabs.addEventHandler(KeyEvent.KEY_PRESSED, moveTurtle);
	}
	
	private void addCommandLine(){
		commandLine.setEditable(true);

	}
	private void removeCommandLine(){
		commandLine.setEditable(true);
		commandLine.setDisable(true);
	}
}
