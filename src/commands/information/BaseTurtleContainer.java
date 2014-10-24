package commands.information;

import java.util.Collection;
import java.util.function.Function;
import javafx.scene.shape.Line;
import GUIFunctions.BorderStyle;
import View.Pen;
import turtle.Position;
import turtle.Turtle;


public abstract class BaseTurtleContainer implements ITurtleBehavior, IInformationContainer {

    public abstract void removeTurtle (int turtleID);

    public abstract void addTurtle (Turtle turtle, boolean isActive);

    public abstract Collection<Turtle> getAllTurtles ();

    public abstract Collection<Integer> getActiveTurtlesByID ();

    public abstract void setActiveTurtles (Collection<Integer> turtleIDs);

    protected abstract Collection<Turtle> getActiveTurtles ();

    private interface TurtleActionWithResult {
        public Object execute (Turtle turtle);
    }

    private Object loopOverTurtleWithResult (TurtleActionWithResult action) {
        Object result = null;
        for (Turtle turtle : getActiveTurtles()) {
            result = action.execute(turtle);
        }
        return result;
    }

    @Override
    public void rotate (double rotateIncrement) {
        loopOverTurtleWithResult(turtle -> {
            turtle.rotate(rotateIncrement);
            return rotateIncrement;
        });
    }

    @Override
    public void move (double xIncrement, double yIncrement) {
        loopOverTurtleWithResult(turtle -> {
            turtle.move(xIncrement, yIncrement);
            return null;
        });
    }

    public double setHeading (double absHeading) {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.setHeading(absHeading));
    }

    public double towardsPosition (double newXPos, double newYPos) {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.towardsPosition(newXPos, newYPos));
    }

    public double setPosition (double newXPos, double newYPos) {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.setPosition(newXPos, newYPos));
    }

    public void moveTowardsHeading (double increment) {
        loopOverTurtleWithResult(turtle -> {
            turtle.moveTowardsHeading(increment);
            return increment;
        });
    }

    public double getOrientation () {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.getOrientation());
    }

    public double getXPos () {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.getXPos());
    }

    public double getYPos () {
        return (Double) loopOverTurtleWithResult(turtle -> turtle.getYPos());
    }

    public void setXPos (double xPos) {
        loopOverTurtleWithResult(turtle -> {
            turtle.setXPos(xPos);
            return xPos;
        });
    }

    public void setYPos (double yPos) {
        loopOverTurtleWithResult(turtle -> {
            turtle.setYPos(yPos);
            return yPos;
        });
    }

    public void setRotation (double rotateVal) {
        loopOverTurtleWithResult(turtle -> {
            turtle.setRotation(rotateVal);
            return rotateVal;
        });
    }

    public void setPenColor (String color) {
        loopOverTurtleWithResult(turtle -> {
            turtle.setPenColor(color);
            return color;
        });
    }

    public void setPenBorderStyle (BorderStyle style) {
        loopOverTurtleWithResult(turtle -> {
            turtle.setPenBorderStyle(style);
            return style;
        });
    }

    public void setPenWidth (Number thickness) {
        loopOverTurtleWithResult(turtle -> { 
            turtle.setPenWidth(thickness);
            return thickness;
        });
    }

    public Line penUndo () {
        return (Line) loopOverTurtleWithResult(turtle -> turtle.penUndo());
    }

    public Pen getPen () {
        return (Pen) loopOverTurtleWithResult(turtle -> turtle.getPen());
    }

    public Position undo (){
        return (Position) loopOverTurtleWithResult(turtle -> turtle.undo());
    }
    

    // private interface TurtleActionWithResult{
    // public double execute(Turtle turtle);
    // }
    //
    // private interface TurtleActionWithoutResult{
    // public void execute(Turtle turtle);
    // }
    //
    // private double loopOverTurtleWithResult(TurtleActionWithResult action){
    // double result = -1;
    // for(Turtle turtle : getActiveTurtles()){
    // result = action.execute(turtle);
    // }
    // return result;
    // }
    //
    // private void loopOverTurtleWithoutResult(TurtleActionWithoutResult action){
    // for(Turtle turtle: getActiveTurtles()){
    // action.execute(turtle);
    // }
    // }
    //
    // @Override
    // public void rotate (double rotateIncrement) {
    // loopOverTurtleWithoutResult(turtle -> turtle.rotate(rotateIncrement));
    // }
    //
    // @Override
    // public void move (double xIncrement, double yIncrement) {
    // loopOverTurtleWithoutResult(turtle -> turtle.move(xIncrement, yIncrement));
    // }
    //
    // public double setHeading(double absHeading) {
    // return loopOverTurtleWithResult(turtle -> turtle.setHeading(absHeading));
    // }
    //
    // public double towardsPosition(double newXPos, double newYPos){
    // return loopOverTurtleWithResult(turtle -> turtle.towardsPosition(newXPos, newYPos));
    // }
    //
    // public double setPosition(double newXPos, double newYPos){
    // return loopOverTurtleWithResult(turtle -> turtle.setPosition(newXPos, newYPos));
    // }
    //
    // public void moveTowardsHeading(double increment){
    // loopOverTurtleWithoutResult(turtle -> turtle.moveTowardsHeading(increment));
    // }
    //
    // public double getOrientation(){
    // return loopOverTurtleWithResult(turtle -> turtle.getOrientation());
    // }
    //
    // public double getXPos(){
    // return loopOverTurtleWithResult(turtle -> turtle.getXPos());
    // }
    //
    // public double getYPos(){
    // return loopOverTurtleWithResult(turtle -> turtle.getYPos());
    // }
    //
    // public void setXPos(double xPos){
    // loopOverTurtleWithoutResult(turtle -> turtle.setXPos(xPos));
    // }
    //
    // public void setYPos(double yPos){
    // loopOverTurtleWithoutResult(turtle -> turtle.setYPos(yPos));
    // }
    //
    // public void setRotation (double rotateVal){
    // loopOverTurtleWithoutResult(turtle -> turtle.setRotation(rotateVal));
    // }
    //
    // public void setPenColor(String color){
    // loopOverTurtleWithoutResult(turtle -> turtle.setPenColor(color));
    // }
    //
    // public void setPenBorderStyle(BorderStyle style){
    // loopOverTurtleWithoutResult(turtle -> turtle.setPenBorderStyle(style));
    // }
    //
    // public void setPenWidth(Number thickness){
    // loopOverTurtleWithoutResult(turtle -> turtle.setPenWidth(thickness));
    // }
    //
    // public Line penUndo(){
    // Line result = null;
    // for(Turtle turtle : getActiveTurtles()) {
    // result = turtle.penUndo();
    // }
    // return result;
    // }
    //
    // public Pen getPen();
    //
    // public Position undo();
}
