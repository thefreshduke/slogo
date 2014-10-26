package communicator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.AnimationTimer;
import turtle.Turtle;
import View.Grid;
import View.SlogoView;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.information.BaseUserDefinedContainer;
import commands.information.IInformationContainer;
import commands.information.IInformationGateway;
import commands.information.MapBasedUserDefinedContainer;
import commands.information.SingleGridInformationGateway;

/**
 * @author Duke, Rahul
 * This class is an implementation of the BaseController designed for asynchronous execution using
 * animation timelines.  BaseController is responsible for ensuring that the different containers are 
 * correctly updated when such information can be provided via the view. It handles error processing and 
 * reporting to the View, as well as provides implementation details for user to load and save functions/variables
 * using Serializable.
 *
 */
public class MainController extends BaseController {

	private static final String ERROR_READING_FROM_FILE = "Error reading from file";
	private static final String ERROR_WRITING_TO_FILE = "Error writing to file";
	private static final String DEFAULT_ENGLISH_FILE = "src/resources/languages/English.properties";
	private SlogoView myView;
	private ConcurrentLinkedQueue<BaseCommand> myCommandQueue;
	private ConcurrentLinkedQueue<String> myInputsToParse;
	private AtomicBoolean myCommandIsExecuting;
	private Collection<BaseCommand> myExecutedCommands;
	private AnimationTimer myCommandExecutionTimer;
	private AnimationTimer myCommandParserTimer;
	private LanguageFileParser myTranslator;
	private CommandToClassTranslator myCommandToClassTranslator;
	private IInformationGateway myInformationGateway;

	private static final String ENGLISH_TO_CLASS_FILE = "src/resources/languages/EnglishToClassName.properties";

	public MainController(SlogoView view) {
		super(view);
		myView = view;
		myCommandQueue = new ConcurrentLinkedQueue<>();
		myInputsToParse = new ConcurrentLinkedQueue<>();
		myCommandIsExecuting = new AtomicBoolean(false);
		myExecutedCommands = new ArrayList<>();
		setTimers();
		myCommandParserTimer.start();
		myCommandExecutionTimer.start();
		myCommandToClassTranslator = new CommandToClassTranslator();
		myInformationGateway = new SingleGridInformationGateway();
		CommandFactory.setInformationGateway(myInformationGateway);
		try {
			CommandFactory.setCommandToClassRelation(myCommandToClassTranslator
					.translateCommandToClass(new File(ENGLISH_TO_CLASS_FILE)));
			myTranslator = new LanguageFileParser(
					new File(DEFAULT_ENGLISH_FILE));
		} catch (BackendException e1) {
			reportErrorToView(e1);
		}
	}

	private void setTimers() {
		myCommandParserTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (!myInputsToParse.isEmpty()) {
					String input = myInputsToParse.poll();
					try {
						myView.setDisable(true);
						BaseCommand command = CommandFactory.createCommand(
								input, false);
						myCommandQueue.add(command);
					} catch (BackendException ex) {
						reportErrorToView(ex);
						myView.setDisable(false);
					}

				}
			}
		};
		myCommandExecutionTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (!myCommandQueue.isEmpty()
						&& !myCommandIsExecuting.getAndSet(true)) {
					BaseCommand command = myCommandQueue.poll();
					executeCommand(command);
				}
			}
		};
	}

	@Override
	public void receiveCommand(String enteredText) {
		try {
			myView.setDisable(true);
			String translatedText = myTranslator
					.translateUserInputIntoEnglish(enteredText);
			myInputsToParse.add(translatedText);
		} catch (BackendException ex) {
			myView.setDisable(false);
			reportErrorToView(ex);
		}
	}

	@Override
	public void start() {
		myCommandParserTimer.start();
		myCommandExecutionTimer.start();
	}

	@Override
	public void pause(int x, int y) {
		myCommandExecutionTimer.stop();
	}

	@Override
	public void stop() {
		myCommandExecutionTimer.stop();
		myCommandParserTimer.stop();
		myInputsToParse.clear();
		myCommandQueue.clear();
	}

	private void executeCommand(BaseCommand command) {
		try {
			command.execute();
			sendUserDefinedVariablesAndCommands();
		} catch (BackendException ex) {
			reportErrorToView(ex);
		} finally {
			myExecutedCommands.add(command);
			myCommandIsExecuting.set(false);
			myView.setDisable(false);
		}

	}

	private void sendUserDefinedVariablesAndCommands() {
		BaseUserDefinedContainer variableContainer = (BaseUserDefinedContainer) myInformationGateway
				.getContainer(BaseUserDefinedContainer.class);
		try {
			Map<String, Double> variableMap = variableContainer
					.getAllVariablesAndValues();
			myView.addVariables(variableMap);
			List<String> customCommandList = variableContainer
					.getAllCustomCommands();
			myView.addUserFunctions(customCommandList);
		} catch (BackendException e) {
			reportErrorToView(e);
		}

	}

	@Override
	protected void reportErrorToView(Exception ex) {
		myView.showError(ex.getMessage());
	}

	@Override
	public void loadLanguage(File file) {
		try {
			myTranslator.extractFromLanguageFile(file);
		} catch (BackendException e) {
			reportErrorToView(e);
		}
	}

	public List<Turtle> getActiveTurtles() {
		BaseTurtleContainer turtleContainer = getTurtleContainer();
		return new ArrayList<>(turtleContainer.getActiveTurtles());
	}

	@Override
	public void addTurtle(Turtle turtle, int gridID, boolean isActive) {
		BaseTurtleContainer turtleContainer = getTurtleContainer();
		turtleContainer.addTurtle(turtle, isActive);
	}

	@Override
	public void addGrid(Grid grid, boolean isActive) {
		BaseGridContainer gridContainer = getGridContainer();
		gridContainer.addGrid(grid, isActive);
	}

	@Override
	public void setGridAsActive(int gridID) {
		BaseGridContainer gridContainer = getGridContainer();
		gridContainer.setGridAsActive(gridID);
	}

	private BaseTurtleContainer getTurtleContainer() {
		return (BaseTurtleContainer) myInformationGateway
				.getContainer(BaseTurtleContainer.class);
	}

	private BaseGridContainer getGridContainer() {
		BaseGridContainer gridContainer = (BaseGridContainer) myInformationGateway
				.getContainer(BaseGridContainer.class);
		return gridContainer;
	}

	@Override
	public IInformationContainer loadPreferences(File file)
			throws BackendException {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		BaseUserDefinedContainer returnContainer = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			returnContainer = (MapBasedUserDefinedContainer) in.readObject();
			in.close();
		} catch (Exception ex) {
			reportErrorToView(new BackendException(ex,
					ERROR_READING_FROM_FILE));
		}
		myInformationGateway.addContainer(returnContainer);
		return (IInformationContainer) returnContainer;
	}

	@Override
	public void savePreferences(IInformationContainer container, String filename)
			throws BackendException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject((MapBasedUserDefinedContainer) container);
			out.close();
		} catch (Exception ex) {
			reportErrorToView(new BackendException(ex, ERROR_WRITING_TO_FILE));
		}
	}

	public void savePreferences(String filename) throws BackendException {
		savePreferences(
				(IInformationContainer) myInformationGateway
						.getContainer(BaseUserDefinedContainer.class),
				filename);

	}

	@Override
	public void setTurtleAsActive(int turtleID) {
		BaseTurtleContainer turtleContainer = (BaseTurtleContainer) myInformationGateway
				.getContainer(BaseTurtleContainer.class);
		turtleContainer.setTurtleAsActive(turtleID);
	}
}
