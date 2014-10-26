package View;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import backendExceptions.BackendException;
import GUIFunctions.Add;
import GUIFunctions.AddTurtle;
import GUIFunctions.AskForInitialFile;
import GUIFunctions.BackgroundColor;
import GUIFunctions.BottomFunctions;
import GUIFunctions.ClearFunction;
import GUIFunctions.ClearStamps;
import GUIFunctions.Function;
import GUIFunctions.GUIFunction;
import GUIFunctions.HelpPage;
import GUIFunctions.LanguageMenu;
import GUIFunctions.PenStyle;
import GUIFunctions.PenThickness;
import GUIFunctions.PersonalizeMenu;
import GUIFunctions.SetBackgroundImage;
import GUIFunctions.SetLanguage;
import GUIFunctions.SetPenDown;
import GUIFunctions.SetPenUp;
import GUIFunctions.Stamp;
import GUIFunctions.ToggleGridLines;
import GUIFunctions.TurtleImageChange;
import GUIFunctions.Undo;
import GUIFunctions.TurtleVariablesTable;
import GUIFunctions.UserInput;
import GUIFunctions.Variable;
import GUIFunctions.VariableTable;
import communicator.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	private GridFactory myGridFactory;
	private GridTracker myGrids;
	private MainController myController;
	private TabsOfGrids myGridTabs=new TabsOfGrids();
	//a Group for all the components of the GUI to be added to
	private Group root=new Group();
	//an ArrayList of all the working commands given by the user
	public Queue<ButtonTemplate> myCommands=new LinkedList<ButtonTemplate>();
	private ColorSelection colorSelection;
	private Scene myScene;
	private TextField commandLine;
	//used to display Turtles most recent stats
	HashMap<String, GUIFunction> myUserFunctions=new HashMap<String, GUIFunction>();
	private VBox commandHistoryBox;
	private MenuTemplate userCommands;
	private Map<String, String> userCommandMap = new HashMap<>();
	private ResourceBundle myResources;
	private Stage myStage;
	private VariableTable myVariableTable;
	private final static Dimension DEFAULT_SIZE=new Dimension(1000,600);
	private static final int MAX_COMMAND_HISTORY = 5;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public SlogoView() throws ClassNotFoundException{
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"Buttons");
		myGrids=new GridTracker();
		colorSelection = new ColorSelection(myGrids);
		makeListOfFunctions();
		myGridFactory=new GridFactory(DEFAULT_SIZE.height, DEFAULT_SIZE.width, this.build(5), myUserFunctions);
		myController=new MainController(this);
		Timeline myTime=new Timeline();
		myTime.setCycleCount(Timeline.INDEFINITE);
		myTime.getKeyFrames().add(this.build(40));
		myTime.play();

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
				if (!myGrids.getActiveGrid().equals(myGridTabs.getActiveGrid())){
					myGrids.setActiveGrid(myGridTabs.getActiveGrid());
					myController.setGridAsActive(myGrids.getActiveGrid().getID());
				}
			}
		};
		return new KeyFrame(speed, loop);
	}
	/**
	 * Creates a layout of the GUI and adds the objects to the Stage
	 * @param mainStage   the Stage for the GUI to operate on 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void initialize(Stage mainStage) {
		addGrid();
		addTurtle();
		myVariableTable=new VariableTable();
		myGridFactory.setGridMap(myUserFunctions);
		myStage=mainStage;
		BorderPane mainLayout=new BorderPane();
		mainLayout.setPrefSize(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		mainLayout.setTop(addMenuBar());
		mainLayout.setCenter(myGrids.getActiveGrid());
		mainLayout.setLeft(setTextArea());
		mainLayout.setBottom(addButtons());
		root.getChildren().add(mainLayout);
		root.getChildren().add(myGridTabs);
		myScene=new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		myStage.setScene(myScene);
		TurtleVariablesTable myTable=new TurtleVariablesTable(myGrids);
	}
	/**
	 * Displays the error message "message" on the screen
	 * 
	 */
	public void showError(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	/**
	 *Passes the commands from the textbox on the GUI to the controller for parsing. Also 
	 *creates a interactive button of the command to be shown in the command history.  
	 * @param s		String representing the command to send that was inputed by the user
	 *  
	 */
	private void sendCommandAndMakeButton(String command){
		myController.receiveCommand(command);

		ButtonTemplate mostRecent = new ButtonTemplate(command, 0, 0, (event -> sendCommandAndMakeButton(command)), 180, 10);
		myCommands.add(mostRecent);
		commandLine.clear();
		updateCommandHistory();
	}


	public void enable(){
		myStage.getScene().getRoot().setDisable(false);
	}
	private MenuBar addMenuBar(){
		MenuBar myMenu=new MenuBar();
		myMenu.setStyle( "-fx-border-width: 5");
		myMenu.setPrefSize(DEFAULT_SIZE.width, 30);
		MenuTemplate fileMenu=this.createfileMenu();
		MenuTemplate languages = new MenuTemplate("Languages");
		MenuTemplate personalize=new MenuTemplate("Personalize");
		MenuTemplate pen=new MenuTemplate("Pen");
		MenuTemplate add=new MenuTemplate("Add");
		MenuTemplate help = new MenuTemplate("Help");
		help.addMenuItem("Help Page", event->myUserFunctions.get("Help").doAction());

		userCommands = new MenuTemplate("User Commands");

		makeAddMenu(add);
		this.makeLanguageMenu(LanguageMenu.class, languages);
		this.makeMenu(PersonalizeMenu.class, personalize);
		this.makeMenu(PenMenu.class, pen);

		myMenu.getMenus().addAll(fileMenu, languages, userCommands, pen, personalize, help, add);
		return myMenu;
	}
	
	/**
	 * Disables all of the buttons on the GUI. Used when a command is sent
	 *  to the backend to prevent concurrency issues.
	 * @param toDisable
	 */
	public void setDisable(Boolean toDisable){
		for (Node myNode: root.getChildren()){
			myNode.setDisable(toDisable);
		}
	}
	
	private void makeLanguageMenu(Class myClass, MenuTemplate myMenu){
		for (String myName: myUserFunctions.keySet()){
			if (myUserFunctions.get(myName) instanceof LanguageMenu){
					LanguageMenu languageFunction=(LanguageMenu) myUserFunctions.get(myName);
					myMenu.addMenuItem(myName, event->myController.loadLanguage(languageFunction.doAction(myResources.getString(myName))));
				}
			}
	}
	
	/**
	 * 
	 * @param myVariables
	 */
	
	public void addVariables(Map<String, Double> myVariables){
		ArrayList<UserInput> myVars=new ArrayList<UserInput>();
		for (String name: myVariables.keySet()){
			Variable myNewVariable=new Variable(name, myVariables.get(name));
			myVars.add(myNewVariable);
		}
		myVariableTable.addInput(myVars);
	}
	
	/**
	 * 
	 * @param myFunctions
	 */
	public void addUserFunctions(List<String> myFunctions){
		ArrayList<UserInput> myInputs=new ArrayList<UserInput>();
		for (String myName: myFunctions){
			UserInput myUserInput=new Function(myName);
			myInputs.add(myUserInput);
		}
		myVariableTable.addInput(myInputs);
	}
	private void makeAddMenu(MenuTemplate myAdd){
		myAdd.addMenuItem("Add Grid", event->addGrid());
		myAdd.addMenuItem("Add Turtle", event->addTurtle());
	}

	private void addTurtle(){
		myController.addTurtle(myGrids.getActiveGrid().addTurtle(), myGrids.getActiveGrid().getID(), true);
	}
	
	/**
	 * Used to create the original grid and any additional grids the user wants.
	 * @return
	 */
	public Grid addGrid() {
		Grid myNewGrid;
		try {
			myNewGrid = myGridFactory.makeGrid("SingleGrid");
			myGrids.setActiveGrid((SingleGrid)myNewGrid);
			myGrids.setActiveGrid((SingleGrid)myNewGrid);
			myGridTabs.addTab("GRID", (SingleGrid)myNewGrid);
			myController.addGrid((SingleGrid)myNewGrid, true);

			return myNewGrid;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * Creates all information on left side of screen
	 * @return
	 */
	private Pane setTextArea(){
		Pane myTextArea=new Pane();
		myTextArea.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myTextArea.setPrefSize(200, DEFAULT_SIZE.height-200);
		createCommandLineWithLabel(myTextArea);
		Button enter = makeEnterButton();
		Button makeCommand = makeCommandButton();
		Text history = makeCommandHistoryLabel();
		makeCommandHistoryBox();
		myTextArea.getChildren().addAll( enter, makeCommand,
				history,  colorSelection, commandHistoryBox);
		return myTextArea;
	}
	
	private Button makeEnterButton(){
		String[] value=myResources.getString("enter").split(";");
		Button enter = new Button (value[0]);
		enter.relocate(Double.parseDouble(value[1]), Double.parseDouble(value[2]));
		enter.setOnAction(event->this.sendCommandAndMakeButton(commandLine.getText()));
		enter.setPrefSize(70, 30);
		return enter;
	}

	private Button makeCommandButton(){
		String[] value=myResources.getString("makeCommand").split(";");
		Button makeCommand = new Button(value[0]);
		makeCommand.setOnAction(event-> makeUserCommand(commandLine.getText()));
		makeCommand.setPrefSize(120, 30);
		makeCommand.relocate(Double.parseDouble(value[1]),Double.parseDouble(value[2]));
		return makeCommand;

	}
	
	private Text makeCommandHistoryLabel(){
		Text history = new Text("  Command History");
		history.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		history.setFill(Color.WHITE);
		history.relocate(0, 350);
		return history;
	}
	private void makeCommandHistoryBox(){
		commandHistoryBox = new VBox();
		commandHistoryBox.setSpacing(10);		
		commandHistoryBox.relocate(0, 380);
	}
	
	private void createCommandLineWithLabel(Pane textArea){
		Label label = new Label("Commands:");
		label.setTextFill(Color.WHITE);
		label.setStyle("-fx-font-size: 25");
		label.setPrefSize(200, 50);
		label.relocate(10, 5);
		commandLine = new TextField();
		commandLine.relocate(5, 60);
		commandLine.setPrefSize(190,100);
		EventHandler enterEvent=new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) 
					sendCommandAndMakeButton(commandLine.getText());		
			}
		};
		commandLine.addEventHandler(KeyEvent.KEY_PRESSED,enterEvent);
		textArea.getChildren().addAll(label, commandLine);
	}

	/**
	 * Creates buttons on bottom of GUI
	 * @return
	 */
	private Pane addButtons() {
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myButtonPanel.getChildren().addAll(this.makeButtons(BottomFunctions.class, "BottomPanel"));
		myButtonPanel.getChildren().addAll(this.makeSlidingBars());
		return myButtonPanel;
	}


	private void updateCommandHistory(){
		commandHistoryBox.getChildren().clear();

		if(myCommands.size() > MAX_COMMAND_HISTORY){
			myCommands.poll();
		}
		for(ButtonTemplate b : myCommands){
			commandHistoryBox.getChildren().add(b);
		}
	}

/**
 * allows the user to save a set of instructions as a menu item for easy access later
 * @param command
 */

	private void makeUserCommand(String command){
		String name = JOptionPane.showInputDialog("Give a Name for your Command");
		userCommandMap.put(name, commandLine.getText());
		commandLine.clear();
		userCommands.addMenuItem(name, event->executeUserCommand(userCommandMap.get(name)));
	}

	private void executeUserCommand(String command){
		myController.receiveCommand(command);
		commandLine.setText(command);
		ButtonTemplate mostRecent = new ButtonTemplate(command,
				0, 0, (event -> sendCommandAndMakeButton(command)), 180,10);
		myCommands.add(mostRecent);
		commandLine.clear();
		updateCommandHistory();
	}

	/**
	 * creates all of the buttons for the GUI from a resource file
	 * @param myClass
	 * @param location
	 * @return
	 */
	private ArrayList<ButtonTemplate> makeButtons(Class myClass, String location){
		Properties prop=new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/"+location+".Properties");
		try {
			prop.load(stream);
			ArrayList<ButtonTemplate> myButtons=new ArrayList<ButtonTemplate>();
			for (String s: myUserFunctions.keySet()){
				if (myUserFunctions.get(s).getClass().getSuperclass().equals(myClass)){
					String[] value=prop.getProperty(s).split(";");
					myButtons.add(new ButtonTemplate(value[0], Double.parseDouble(value[1]), Double.parseDouble(value[2]), myUserFunctions.get(s)));
				}
			}
			return myButtons;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Creates all of the menus for the GUI from a resource file
	 * @param myClass
	 * @param myMenu
	 */
	private void makeMenu(Class myClass, MenuTemplate myMenu){
		for (String s: myUserFunctions.keySet()){
			Class<? extends GUIFunction> myFunctionClass=(Class<? extends GUIFunction>) myUserFunctions.get(s).getClass().getSuperclass();
			if (myFunctionClass.equals(myClass)){
				myMenu.addMenuItem(s, event->myUserFunctions.get(s).doAction());
			}
		}
	}
	private Collection<ScrollingBar> makeSlidingBars(){
		Collection<ScrollingBar> myListOfBars=new ArrayList<ScrollingBar>();
		ScrollingBar myPenBar=new PenScrollingBar(myResources.getString("penThickness"), 250, 20, myUserFunctions.get("penThickness"));
		myListOfBars.add(myPenBar);
		return myListOfBars;
	}

	/**
	 * Creates a list of functions to be used with the GUI's buttons, menus, etc.
	 */
	private void makeListOfFunctions(){
		myUserFunctions.put("penDown", new SetPenDown(myGrids));
		myUserFunctions.put("undo", new Undo(myGrids));
		myUserFunctions.put("clear", new ClearFunction(myGrids));
		myUserFunctions.put("penUp", new SetPenUp(myGrids));
		myUserFunctions.put("uploadBackgroundImage", new SetBackgroundImage(myGrids, myStage));
		myUserFunctions.put("toggleReferenceGrid", new ToggleGridLines(myGrids, 50));
		myUserFunctions.put("uploadTurtleImage", new TurtleImageChange(myGrids, myStage));
		myUserFunctions.put("addTurtle", new AddTurtle(myGrids));
		myUserFunctions.put("dottedPenStyle", new PenStyle(myGrids,"Dotted"));
		myUserFunctions.put("solidPenStyle", new PenStyle(myGrids, "Solid"));
		myUserFunctions.put("dashedPenStyle", new PenStyle(myGrids, "Dashed"));
		myUserFunctions.put("stampTurtle", new Stamp(myGrids));
		myUserFunctions.put("helpPage", new HelpPage());
		myUserFunctions.put("backgroundColor", new BackgroundColor(myGrids, colorSelection));
		myUserFunctions.put("penColor", new PenColor(myGrids,colorSelection));
		myUserFunctions.put("penThickness", new PenThickness(myGrids));
		myUserFunctions.put("setPalette", new SetPalette(colorSelection));
		myUserFunctions.put("uploadFile", new AskForInitialFile());
		myUserFunctions.put("clearStamp", new ClearStamps(myGrids));
		addLanguages();

	}
	private void addLanguages(){
		try {
			Properties prop = new Properties();
			InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/Languages.Properties");
			prop.load(stream);
			for(Object language : prop.keySet()){
				myUserFunctions.put((String) language, new SetLanguage()); 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Language File not Found, using default colors");	
		}
	}
	private MenuTemplate createfileMenu(){
		MenuTemplate file=new MenuTemplate("File");
		file.addMenuItem("Export", event->saveFileToController(saveFile()));
		AskForInitialFile myFunction=(AskForInitialFile)myUserFunctions.get("uploadFile");
		file.addMenuItem(myResources.getString("uploadFile"),event->checkNullFile(myFunction.sendFile()));
		return file;
	}
	private String saveFile(){
		return JOptionPane.showInputDialog(null, "Name of desired file to save to:");
	}
	private void saveFileToController(String fileName){
		try {
			myController.savePreferences(fileName);
		} catch (BackendException e) {
			JOptionPane.showMessageDialog(null, "No input");
		}
	}
	private void checkNullFile(File myFile){
		if (myFile!=null){
			try {
				myController.loadPreferences(myFile);
			} catch (BackendException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "The file was null");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "The file was null");
		}
	}
}
