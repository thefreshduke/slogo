package communicator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import View.SlogoView;
import backendExceptions.BackendException;
import turtle.Position;
import turtle.Turtle;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class MainController extends BaseController {

    private SlogoView myView;
    private SlogoModel myModel;
    private ConcurrentLinkedQueue<BaseCommand> myCommandQueue;
    private ConcurrentLinkedQueue<String> myInputsToParse;

    private AtomicBoolean myCommandIsExecuting;

    private Collection<BaseCommand> myExecutedCommands;

    private AnimationTimer myCommandExecutionTimer;
    private AnimationTimer myCommandParserTimer;
    private LanguageFileParser myTranslator;
    private CommandToClassTranslator myCommandToClassTranslator;

    private IVariableContainer myVariableContainer;
    
    private static final String ENGLISH_TO_CLASS_FILE = "src/resources/languages/EnglishToClassName.properties";
    public MainController (SlogoView view) {
        super(view);
        myView = view;
        myModel = new SlogoModel();
        myCommandQueue = new ConcurrentLinkedQueue<>();
        myInputsToParse = new ConcurrentLinkedQueue<>();
        myCommandIsExecuting = new AtomicBoolean(false);
        myExecutedCommands = new ArrayList<>();
        setTimers();
        initializeModel();
        myCommandParserTimer.start();
        myCommandExecutionTimer.start();
        myCommandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory
                    .setCommandToClassRelation(myCommandToClassTranslator
                            .translateCommandToClass(new File(ENGLISH_TO_CLASS_FILE)));
        }
        catch (BackendException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            myTranslator = new LanguageFileParser(new File("src/resources/languages/English.properties"));
        }
        catch (BackendException e) {
            System.out.println("ff'");
        }
        myVariableContainer = new MapBasedVariableContainer();
    }

    private void setTimers () {
        myCommandParserTimer = new AnimationTimer() {

            @Override
            public void handle (long now) {
                if (!myInputsToParse.isEmpty()) {
                    String input = myInputsToParse.poll();
                    // try{
                    // BaseCommand command = myFactory.createCommand(input);
                    // myCommandQueue.add(command);
                    // }
                    // catch(BackendException ex) {
                    // reportErrorToView(ex);
                    // }
                    BaseCommand command = CommandFactory.createCommand(input, false);
                    myCommandQueue.add(command);

                }
            }
        };
        myCommandExecutionTimer = new AnimationTimer() {

            @Override
            public void handle (long now) {
                if (!myCommandQueue.isEmpty() && !myCommandIsExecuting.getAndSet(true)) {
                    BaseCommand command = myCommandQueue.poll();
                    executeCommand(command);

                }
            }
        };
    }

    @Override
    public void receiveCommand (String enteredText) {
        String translatedText = myTranslator.translateUserInputIntoEnglish(enteredText);
        myInputsToParse.add(translatedText);
    }

    @Override
    protected void initializeModel () {
        myModel.initializeModel();
    }

    @Override
    public void start () {
        myCommandParserTimer.start();
        myCommandExecutionTimer.start();
    }

    @Override
    public void pause (int x, int y) {
        myCommandExecutionTimer.stop();
    }

    @Override
    public void stop () {
        myCommandExecutionTimer.stop();
        myCommandParserTimer.stop();
        myInputsToParse.clear();
        myCommandQueue.clear();
    }

    @Override
    public void hardSetTurtle (double x, double y, double orientationAngle) {
        myModel.hardSetTurtle(x, y, orientationAngle);
    }

    private void executeCommand (BaseCommand command) {
        try{
            command.execute(myView, myModel.getTurtle(), myVariableContainer);
            myExecutedCommands.add(command);
            myCommandIsExecuting.set(false);
        }
        catch(BackendException ex){
            reportErrorToView(ex);
        }
    }

    @Override
    protected void reportErrorToView (Exception ex) {
        //TODO: view takes error myView.
        
    }

    @Override
    public void loadLanguage (File file) {
        try {
            myTranslator.extractFromLanguageFile(file);
        }
        catch (BackendException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

	@Override
	public Turtle getTurtle() {
		return myModel.getTurtle();
	}

	/*@Override
	public void setTurtleImage(Image image) {
		// TODO Auto-generated method stub
		
	}*/
}
