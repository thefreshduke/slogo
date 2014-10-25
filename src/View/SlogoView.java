package View;

import java.awt.Dimension;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import GUIFunctions.Add;
import GUIFunctions.AddTurtle;
import GUIFunctions.BackgroundColor;
import GUIFunctions.BottomFunctions;
import GUIFunctions.ClearFunction;
import GUIFunctions.ColorFunction;
import GUIFunctions.ControllerFunctions;
import GUIFunctions.GUIFunction;
import GUIFunctions.HelpPage;
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
import GUIFunctions.VariableTable;
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
import javafx.scene.control.ScrollBar;
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
	private Text lastX, lastY, lastOrientation;
	HashMap<String, GUIFunction> myUserFunctions=new HashMap<String, GUIFunction>();
	String penColor;
	//for displaying command history
	private VBox commandHistoryBox;
	private MenuTemplate userCommands;
	private ResourceBundle myResources;
	private Stage myStage;
	
	public final static Dimension DEFAULT_SIZE=new Dimension(1000,600);
	private static final int MAX_COMMAND_HISTORY = 5;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public SlogoView() throws ClassNotFoundException{
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"Buttons");
		myGridFactory=new GridFactory(DEFAULT_SIZE.height-100, DEFAULT_SIZE.width-200, this.build(5), null);
		myGrids=new GridTracker();
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
				myGrids.setActiveGrid(myGridTabs.getActiveGrid());
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
		//
		makeListOfFunctions();
		colorSelection = new ColorSelection(myGrids);
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
		myGrids.getActiveGrid().addTurtle(myController.getFirstTurtle());
		myController.addGrid(myGrids.getActiveGrid(),true);
		VariableTable myTable=new VariableTable(myGrids);
	}
	/**
	 * Displays the error message "message" on the screen
	 * 
	 */
	public void showError(String message){
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
		MenuTemplate personalize=new MenuTemplate("Personalize");
		MenuTemplate help = new MenuTemplate("Help");
		help.addMenuItem("Help Page", event->myUserFunctions.get("Help").doAction());
		MenuTemplate pen=new MenuTemplate("Pen");
		userCommands = new MenuTemplate("User Commands");
		MenuTemplate add=new MenuTemplate("Add");
		makeAddMenu(add);
	//	this.makeLanguageMenuItems(languages);
		this.makeMenu(PersonalizeMenu.class, personalize);
		this.makeMenu(PenMenu.class, pen);
		
		myMenu.getMenus().addAll(fileMenu, languages, userCommands, pen, personalize, help, add);
		return myMenu;
	}
	private void makeAddMenu(MenuTemplate myAdd){
		myAdd.addMenuItem("Add Grid", event->addGrid());
		Add newAddFunction=(Add) myUserFunctions.get("addTurtle");
		myAdd.addMenuItem("Add Turtle", event->myGrids.getActiveGrid().addTurtle());
	}

	private HashMap<String, GUIFunction> makePenMap(){
		HashMap<String, GUIFunction> myMap=new HashMap<String, GUIFunction>();

		return myMap;

	}
	private void makeMenuItems(HashMap<String, GUIFunction> myMap, MenuTemplate myMenu){
		for (String name: myMap.keySet()){
			myMenu.addMenuItem(name, event->myMap.get(name).doAction());
		}
	}
	private void createMenuItems(MenuTemplate pMenu, String resource, HashMap<String, GUIFunction> myMap){
		/*
		ResourceBundle personalizeItems=ResourceBundle.getBundle("/resources/Personalize.Properties");
		for (String s: myMap.keySet()){
			pMenu.addMenuItem(personalizeItems.getString(s), event->myMap.get(s).doAction());
		}
		 */

	}


	public HashMap<String, GUIFunction> makePersonalizeMap(){
		HashMap<String, GUIFunction> myMap=new HashMap<String, GUIFunction>();

		return myMap;
	}
	
	private void createMenuItemsUnderFile(MenuTemplate fileMenu) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		fileMenu.addMenuItem("Export to XML", null);
		fileMenu.addMenuItem("Import to XML", null);
		fileMenu.addMenuItem("Add Grid", event->addGrid());
	}
	private void addTurtle(){
		//myController.addTurtle(myGrids.getActiveGrid().addTurtle(), myGrids.getActiveGrid().getID(), true);
		

	}
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
		EventHandler enterEvent=new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) 
					sendCommand();		
			}
		};
		commandLine.addEventHandler(KeyEvent.KEY_PRESSED,enterEvent);
		String[] value=myResources.getString("enter").split(";");
		Button enter = new Button (value[0]);
		enter.relocate(Double.parseDouble(value[1]), Double.parseDouble(value[2]));
		enter.setOnAction(event->this.sendCommand());
		enter.setPrefSize(70, 30);
		commandLine.relocate(5, 60);
		commandLine.setPrefSize(190,100);
		value=myResources.getString("makeCommand").split(";");
		Button makeCommand = new Button(value[0]);
		makeCommand.setOnAction(event-> makeUserCommand(commandLine.getText()));
		makeCommand.setPrefSize(120, 30);
		makeCommand.relocate(Double.parseDouble(value[1]),Double.parseDouble(value[2]));

/*		
		ColorBar backGroundColorBar=new ColorBar(myResources.getString("backgroundColor"));
		backGroundColorBar.relocate(25, 350);
		ColorBar penColorBar=new ColorBar(myResources.getString("penColor"));
		backGroundColorBar.relocate(25, 350);
		penColorBar.relocate(25, 385);
		Iterator<String>myIterator=colorSelection.getAvailableColors().iterator();
		while (myIterator.hasNext()){
			String color=(String)myIterator.next();
			ColorFunction myFunction= (ColorFunction) myUserFunctions.get("backgroundColor");
			backGroundColorBar.addItem(color, event->myFunction.doAction(color));
			ColorFunction myPenFunction=(ColorFunction) myUserFunctions.get("penColor");
			penColorBar.addItem(color, event->myPenFunction.doAction(color));
		}
	*/
		commandHistoryBox = new VBox();
		Text history = new Text("  Command History");
		history.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		history.setFill(Color.WHITE);
		history.relocate(0, 350);
		commandHistoryBox.setSpacing(10);		
		updateCommandHistory();
		commandHistoryBox.relocate(0, 380);
		System.out.println(colorSelection);
		myTextArea.getChildren().addAll(label, commandLine, enter, makeCommand,
				 history, commandHistoryBox, colorSelection);
		return myTextArea;
	}


	private Pane addButtons() {
		Pane myButtonPanel=new Pane();
		myButtonPanel.setPrefSize(DEFAULT_SIZE.width, 75);
		myButtonPanel.setStyle("-fx-background-color: #000080; -fx-border-color: BLACK; -fx-border-width: 5");
		myButtonPanel.getChildren().addAll(this.makeButtons(BottomFunctions.class, "BottomPanel"));
		myButtonPanel.getChildren().addAll(this.makeSlidingBars());
		return myButtonPanel;
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
		myGrids.getActiveGrid().setBackgroundColor(color);
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

	private ArrayList<ButtonTemplate> makeButtons(Class myClass, String location){
		Properties prop=new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/"+location+".Properties");
		try {
			prop.load(stream);
			ArrayList<ButtonTemplate> myButtons=new ArrayList<ButtonTemplate>();
			for (String s: myUserFunctions.keySet()){
				if (myUserFunctions.get(s).getClass().getSuperclass().equals(myClass)){
					System.out.println(myUserFunctions.get(s));
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
		ScrollingBar myPenBar=new PenScrollingBar(myResources.getString("penThickness"), 200, 20, myUserFunctions.get("penThickness"));
		myListOfBars.add(myPenBar);
		return myListOfBars;
	}

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
	//	myUserFunctions.put(myResources.getString("spanish"), new SetLanguage());
		myUserFunctions.put("helpPage", new HelpPage());
		myUserFunctions.put("backgroundColor", new BackgroundColor(myGrids));
		myUserFunctions.put("penColor", new PenColor(myGrids));
		myUserFunctions.put("penThickness", new PenThickness(myGrids));
	}

}
