package View;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import GUIFunctions.GUIFunction;
import turtle.Position;
import turtle.Turtle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class SingleGrid extends Grid {
	
    private final static Dimension DEFAULT_GRID_SIZE = new Dimension(800, 500);
    private String backgroundColor = "FFFFFF";
    private int myHeight;
    private int myWidth;
    private ImageView myImageView;
    private HashSet<Turtle> allTurtles=new HashSet<Turtle>();
    private HashSet<Turtle> activeTurtles=new HashSet<Turtle>();
    private HashMap<String, GUIFunction> myGridFunctions=new HashMap<String, GUIFunction>();
    public SingleGrid(){


    }
    public SingleGrid(KeyFrame frame, HashMap myMap, int ID){
        this.setStyle("-fx-border-color: BLACK; -fx-border-width: 10");
        myID=ID;
        myHeight=DEFAULT_GRID_SIZE.height;
        myWidth=DEFAULT_GRID_SIZE.width;
        this.setPrefSize(myWidth, myHeight);
        Timeline time=new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        setBackgroundColor(backgroundColor);
        myGridFunctions=myMap;
    }
    public int getMyHeight(){
    	return myHeight;
    }
    public int getMyWidth(){
    	return myWidth;
    }
    public Collection<Turtle> getAllTurtles(){
        return allTurtles;
    }
    public Turtle addTurtle(){
        Image image = new Image("bowser.png");
        Turtle myTurtle = new Turtle(new Position(0, 0), image);
        myTurtle.setID(getAllTurtles().size());
        myTurtle.setFitWidth(60);
        myTurtle.setPreserveRatio(true);
        myTurtle.setSmooth(true);
        return addTurtle(myTurtle);
    }
    public void setBackgroundColor(String color){
        backgroundColor = color;
        this.getChildren().remove(myImageView);
        setStyle("-fx-background-color: #"+backgroundColor);
    }
    public void moveTurtle(Turtle t){
        t.relocate(t.getXPos(), t.getYPos());
        t.setRotate(t.getOrientation());
    }

    /**
     * Takes in the coordinates (x,y) from the controller and pops the last coordinate from the stack to call move(int x, int y)
     * @param x		x location on the Grid
     * @param y		y location on the Grid
     */
    public void update(Collection<Turtle> activatedTurtles){
        activeTurtles.clear();
        for (Turtle active: activatedTurtles){
            moveTurtle(active);
            getChildren().add(active.getPen().drawLine(active.getXPos(), active.getYPos()));
            activeTurtles.add(active);
        }	
    }
   
    public void keyUpdate(){
        for (Turtle active: getActiveTurtles()){
            moveTurtle(active);
            getChildren().add(active.getPen().drawLine(active.getXPos(), active.getYPos()));
        }	
    }
    public Collection<Turtle> getActiveTurtles(){
        activeTurtles.clear();
        for (Turtle a: allTurtles){
            if (a.isActive()){
                activeTurtles.add(a);
            }
        }
        return activeTurtles;
    }
    public Collection<Pen> getAllPens(){
        return getPens(activeTurtles);
    }
    public Collection<Pen> getActivePens(){
        return getPens(getActiveTurtles());
    }
    private Collection<Pen> getPens(Collection<Turtle> myTurtles){
        Collection<Pen> pens=new ArrayList<Pen>();
        for (Turtle t: myTurtles){
            pens.add(t.getPen());
        }
        return pens;
    }
    public void updateGUI(String myFunction){
        myGridFunctions.get(myFunction).doAction();
    }
    public void updateGUI(String myFunction, List<? extends Number> myNumber){
        myGridFunctions.get(myFunction).doAction(myNumber);
    }
    public void sendErrorMessage(String s){
        JOptionPane.showMessageDialog(null, s);
    }
    public Turtle addTurtle(Turtle myTurtle){
        allTurtles.add(myTurtle);
        activeTurtles.add(myTurtle);
        getChildren().add(myTurtle);
        myTurtle.move(myWidth/2,myHeight/2);
        myTurtle.getPen().setInitialPosition(myTurtle.getXPos(), myTurtle.getYPos());
        moveTurtle(myTurtle);
        return myTurtle;
    }
    @Override
    public void setPalette(Double myRed, Double myGreen, Double myBlue) {
        // TODO Auto-generated method stub		
    }	
}
