package commands.information;

import java.util.Collection;
import turtle.Turtle;


public abstract class BaseTurtleContainer implements ITurtleBehavior, IInformationContainer {

    @Override
    public void rotate (double rotateIncrement) {
        // TODO Auto-generated method stub
        for (Turtle turtle : getActiveTurtlesByTurtle()) {
            turtle.rotate(rotateIncrement);
        }
    }

    @Override
    public void move (double xIncrement, double yIncrement) {
        for (Turtle turtle : getActiveTurtlesByTurtle()) {
            turtle.move(xIncrement, yIncrement);
        }
    }

    public abstract void removeTurtle(int turtleID);
    
    public abstract void addTurtle(boolean isActive);
    
    public abstract Collection<Turtle> getAllTurtles();

    public abstract Collection<Integer> getActiveTurtlesById();

    public abstract void setActiveTurtles (Collection<Integer> turtleIDs);

    protected abstract Collection<Turtle> getActiveTurtlesByTurtle();
}
