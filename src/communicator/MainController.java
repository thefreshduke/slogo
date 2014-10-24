package communicator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.SlogoModel;
import View.Grid;
import View.SlogoView;
import backendExceptions.BackendException;
import turtle.Turtle;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.information.ICommandInformationHub;
import commands.information.IInformationContainer;
import commands.information.BaseVariableContainer;
import javafx.animation.AnimationTimer;


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
    private ICommandInformationHub myCommandInformationHub;

    private static final String ENGLISH_TO_CLASS_FILE =
            "src/resources/languages/EnglishToClassName.properties";

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
            myTranslator =
                    new LanguageFileParser(new File("src/resources/languages/English.properties"));
        }
        catch (BackendException e) {
            System.out.println("ff'");
        }
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
                    BaseCommand command = myModel.createInitialCommand(input);
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
        try {
            command.execute(myView, myModel.findTurtle(0), myModel.getMyVariableContainer());
            myExecutedCommands.add(command);
            myCommandIsExecuting.set(false);
        }
        catch (BackendException ex) {
            reportErrorToView(ex);
        }
    }

    @Override
    protected void reportErrorToView (Exception ex) {
        // TODO: view takes error myView.

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

    /**
     * Find turtle matching specified ID
     * 
     * @param ID of turtle
     * @return turtle matching ID, else return null if no turtle match
     */
    public Turtle findTurtle (int ID) {
        return myModel.findTurtle(ID);
    }

    public List<Turtle> getActiveTurtles () {
        return myModel.getActiveTurtles();
    }

    public Turtle getFirstTurtle () {
        // TODO Auto-generated method stub
        return myModel.findTurtle(0);
    }

    @Override
    public void addTurtle (Turtle turtle, int ID, boolean isActive) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addGrid (Grid grid, boolean isActive) {
        // TODO Auto-generated method stub

    }

    @Override
    public IInformationContainer loadPreferences (IInformationContainer container, File file)
                                                                                             throws BackendException {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        BaseVariableContainer returnContainer = null;
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            returnContainer = (BaseVariableContainer) in.readObject();
            in.close();
        }
        catch (Exception ex) {
            throw new BackendException(ex, "Error reading from file");
        }

        return (IInformationContainer) returnContainer;
    }

    @Override
    public void savePreferences (IInformationContainer container, String filename)
                                                                                  throws BackendException {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject((BaseVariableContainer) container);
            out.close();
        }
        catch (Exception ex) {
            throw new BackendException(ex, "Error writing to file");
        }
    }

    /*
     * @Override
     * public void setTurtleImage(Image image) {
     * // TODO Auto-generated method stub
     * 
     * }
     */
}
