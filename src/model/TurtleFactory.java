package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class TurtleFactory {

    private List<Turtle> myActiveTurtles = new ArrayList<Turtle>();

    public void createTurtle (Image image) {
        Turtle turtle = new Turtle(new Position(0, 0), image);
        turtle.setFitWidth(60);
        turtle.setPreserveRatio(true);
        turtle.setSmooth(true);
        if (myActiveTurtles.size() == 0) {
            turtle.setID(0);
        } else {
            turtle.setID(myActiveTurtles.size() - 1);
        }

        myActiveTurtles.add(turtle);
    }

    public List<Turtle> getActiveTurtles () {
        return myActiveTurtles;
    }

    public Turtle findTurtle (int ID) {
        for (int i = 0; i < myActiveTurtles.size(); i++) {
            if (i == ID) {
                return myActiveTurtles.get(i);
            }
        }
        return null;
    }
}