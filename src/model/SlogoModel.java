package model;

import java.util.List;

import javafx.scene.image.Image;
import turtle.Turtle;

import commands.information.BaseUserDefinedContainer;
import commands.information.MapBasedUserDefinedContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class SlogoModel {
    private static final String INITIAL_TURTLE_SPRITE = "bowser.png";
    private Turtle myTurtle;
    private BaseUserDefinedContainer myVariableContainer;
    private TurtleFactory myTurtleFactory;
    public void initializeModel () {
        myVariableContainer = new MapBasedUserDefinedContainer();
        initializeTurtle(new Image(INITIAL_TURTLE_SPRITE));
    }	

    public void hardSetTurtle (double x, double y, double orientationAngle) {
        myTurtle.setXPos(x);
        myTurtle.setYPos(y);
        myTurtle.setRotation(orientationAngle);
    }

    public void initializeTurtle(Image image) {
        myTurtleFactory = new TurtleFactory();
        myTurtleFactory.createTurtle(image);
    }
    public List<Turtle> getActiveTurtles() {
        return myTurtleFactory.getActiveTurtles();
    }
    
    public Turtle findTurtle(int ID) {
        return myTurtleFactory.findTurtle(ID);
    }
}