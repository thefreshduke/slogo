package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import turtle.Turtle;

public class TurtleList extends BaseTurtleContainer{
    
    private Collection<Turtle> myTurtleList;
    private Collection<Turtle> myActiveTurtleList;
    
    public TurtleList(Turtle turtle){
        myTurtleList = new HashSet<>();
        myActiveTurtleList = new HashSet<>();
        myTurtleList.add(turtle);
        myActiveTurtleList.add(turtle);
    }
    @Override
    public void removeTurtle (int turtleID) {
        Turtle turtleToRemove = null;
        for(Turtle turtle : myTurtleList){
            if(turtle.getID() == turtleID){
                turtleToRemove = turtle;
            }
        }
        if(turtleToRemove != null){
            myTurtleList.remove(turtleToRemove);
            myActiveTurtleList.remove(turtleToRemove);
        }
    }

    @Override
    public void addTurtle (Turtle turtle, boolean isActive) {
        myTurtleList.add(turtle);
        if(isActive){
            myActiveTurtleList.add(turtle);
        }
    }

    @Override
    public Collection<Turtle> getAllTurtles () {
        return new ArrayList<>(myTurtleList);
    }

    @Override
    public Collection<Integer> getActiveTurtlesByID () {
        HashSet<Integer> activeTurtleIDs = new HashSet<>();
        for(Turtle turtle : myActiveTurtleList){
            if(turtle != null){
                activeTurtleIDs.add(turtle.getID());
            } 
        }
        return activeTurtleIDs;
    }

    @Override
    public void setActiveTurtles (Collection<Integer> turtleIDs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<Turtle> getActiveTurtles () {
        return new ArrayList<>(myActiveTurtleList);
    }

}
