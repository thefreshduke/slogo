package View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import javax.swing.JOptionPane;

import GUIFunctions.BackgroundColor;

public class ColorSelection extends Pane {

    private SlogoView myView;
    private ArrayList<String> myColors = new ArrayList<>();
    private HashSet<ColorLabel> myLabels = new HashSet();
    private GridTracker myGrids;
    private HBox colorBox;

    public ColorSelection (GridTracker grid) {
        myGrids = grid;

        myLabels.add(new ColorLabel(0, 0, 80, 20, "BackgroundColor", new BackgroundColor(myGrids)));
        myLabels.add(new ColorLabel(100, 0, 80, 20, "Pen Color", new PenColor(myGrids)));

        this.relocate(0, 250);
        try {
            Properties prop = new Properties();
            InputStream stream = getClass().getClassLoader().getResourceAsStream(
                    "./resources/Colors.Properties");
            prop.load(stream);
            for (Object color : prop.keySet()) {
                myColors.add(prop.getProperty((String) color));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Color File not Found, using default colors");
            myColors.add("BLACK");
            myColors.add("WHITE");
        }

        this.setPrefSize(200, 200);
        this.getChildren().addAll(myLabels);
        myColorPane();
        this.getChildren().add(colorBox);

    }

    public Collection<String> getAvailableColors () {
        return myColors;
    }

    private void myColorPane () {
        colorBox.getChildren().clear();
        colorBox = new HBox();
        for (String color : myColors) {
            Circle myCircle = new Circle();
            myCircle.setFill(Paint.valueOf(color));
            myCircle.setOnMouseClicked(event -> doColorEvent(color));
            myCircle.setRadius(10);
            colorBox.getChildren().add(myCircle);
        }
        colorBox.relocate(30, 50);
    }

    private void doColorEvent (String color) {
        for (ColorLabel myLabel : myLabels) {
            myLabel.isActive(color);
        }
    }

    public void setColor (int myIndex, String color) {
        if (myColors.contains(color)) {
            switchPosition(myColors.indexOf(color), color, myIndex, myColors.get(myIndex));
        } else {
            myColors.add(color);
            switchPosition(myColors.indexOf(color), color, myIndex, myColors.get(myIndex));
        }
        myColorPane();
    }

    public void switchPosition (int firstIndex, String firstString, int secondIndex,
            String secondString) {
        myColors.set(secondIndex, firstString);
        myColors.set(firstIndex, secondString);

    }

}
