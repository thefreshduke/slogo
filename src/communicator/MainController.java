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
import commands.BaseCommand;
import commands.TestFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class MainController extends BaseController {
    
    private SlogoView myView;
    private Turtle myTurtle;
    private ConcurrentLinkedQueue<BaseCommand> myCommandQueue;
    private ConcurrentLinkedQueue<String> myInputsToParse;
    
    private AtomicBoolean myCommandIsExecuting;
    
    private Collection<BaseCommand> myExecutedCommands;
    
    private AnimationTimer myCommandExecutionTimer;
    private AnimationTimer myCommandParserTimer;
    
    private CommandFactory myFactory; 
    
    public MainController (SlogoView view) {
        super(view);
        myView = view;
        
        myCommandQueue = new ConcurrentLinkedQueue<>();
        myInputsToParse = new ConcurrentLinkedQueue<>();
        myCommandIsExecuting = new AtomicBoolean(false);
        myExecutedCommands = new ArrayList<>();
        setTimers();
        initializeModel();
    }

    private void setTimers(){
        myCommandParserTimer = new AnimationTimer() {
            
            @Override
            public void handle (long now) {
                if(!myInputsToParse.isEmpty()){
                    String input = myInputsToParse.poll();
//                    try{
//                        BaseCommand command = myFactory.createCommand(input);
//                        myCommandQueue.add(command);
//                    }
//                    catch(BackendException ex) {
//                        reportErrorToView(ex);
//                    }
                      BaseCommand command = TestFactory.createCommand(input, true);
                      myCommandQueue.add(command);
                    
                }
            }
        };
        myCommandExecutionTimer = new AnimationTimer() {
            
            @Override
            public void handle (long now) {
                if(!myCommandQueue.isEmpty() && !myCommandIsExecuting.getAndSet(true)){
//                    try{
//                        BaseCommand command = myCommandQueue.poll();
//                        executeCommand(command); 
//                    }
//                    catch(BackendException ex){
//                        reportErrorToView(ex);
//                    }
                	
                }
            }
        };
    }
    
    @Override
    public void receiveCommand (String enteredText) {
        myInputsToParse.add(enteredText);
    }

    @Override
    protected void initializeModel () {
    	Image image = new Image("\bowser.png");
        myTurtle = new Turtle(new Position(0,0), image);
        myTurtle.setFitWidth(60);
        myTurtle.setPreserveRatio(true);;
        myTurtle.setSmooth(true);
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
        myTurtle.setXPos(x);
        myTurtle.setYPos(y);
        myTurtle.setRotation(orientationAngle);
    }
    
    private void executeCommand(BaseCommand command){
        command.execute(myView, myTurtle);
        myExecutedCommands.add(command);
        myCommandIsExecuting.set(false);
    }

	@Override
	public Turtle getTurtle() {
		return myTurtle;
	}

	@Override
	public void setTurtleImage(Image image) {
		myTurtle.setImage(image);
	}
}