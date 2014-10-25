package GUIFunctions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import turtle.Turtle;
import View.Grid;
import View.GridTracker;
import View.SingleGrid;

public class VariableTable extends TableView {
    GridTracker allGrids;
    Collection<Column> myColumns;

    public VariableTable (GridTracker grid) {
        myColumns = new HashSet<Column>();
        allGrids = grid;
        if (makeMap()) {
            Scene scene = new Scene(new Group());
            Stage newStage = new Stage();
            newStage.setTitle("Variable Table");
            newStage.setWidth(300);
            newStage.setHeight(500);
            Label label = new Label("MyTurtleTable");
            label.setFont(new Font("Arial", 20));
            Timeline time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            time.getKeyFrames().add(build());
            time.play();
            setEditable(true);
            setColumnEditable();
            getColumns().addAll(myColumns);
            updateList();
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.getChildren().addAll(label, this);
            ((Group) scene.getRoot()).getChildren().addAll(vbox);
            newStage.setScene(scene);
            newStage.show();
        }

    }

    private KeyFrame build () {
        Duration speed = Duration.millis(1000 / 100);
        final EventHandler<ActionEvent> loop = new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent evt) {
                updateList();
            }
        };
        return new KeyFrame(speed, loop);
    }

    private void updateList () {
        this.getItems().clear();
        ObservableList<Turtle> myObservableList = FXCollections.observableArrayList();
        for (Grid grid : allGrids) {
            SingleGrid myGrid = (SingleGrid) grid;
            for (Turtle t : myGrid.getAllTurtles()) {
                myObservableList.add(t);
            }
        }
        this.setItems((myObservableList));
    }

    private void setColumnEditable () {
        for (Column<Object> myCurrentColumn : myColumns) {
            myCurrentColumn.setOnEditCommit(new EventHandler<CellEditEvent<Turtle, Object>>() {
                @Override
                public void handle (CellEditEvent<Turtle, Object> myTurtle) {
                    moveTurtle(myCurrentColumn, myTurtle);
                }
            });
        }

    }

    private void moveTurtle (Column myColumn, CellEditEvent<Turtle, Object> myTurtle) {
        for (Grid grid : allGrids) {
            SingleGrid myGrid = (SingleGrid) grid;
            Turtle turtle = (Turtle) myTurtle.getTableView().getItems()
                    .get(myTurtle.getTablePosition().getRow());
            if (myGrid.getActiveTurtles().contains(turtle)) {
                myColumn.doEditingFunction(myTurtle, turtle);
                Collection<Turtle> myTurtleCollection = new ArrayList<Turtle>();
                myTurtleCollection.add(turtle);
                myGrid.update(myTurtleCollection);
                break;
            }
        }
    }

    private boolean makeMap () {
        Properties prop = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                "./resources/VariablesForTable.Properties");
        try {
            prop.load(stream);
        } catch (IOException e) {
            return false;
        }

        for (Object columns : prop.keySet()) {
            String[] myValues = prop.getProperty((String) columns).split(";");
            Class<? extends Column> myClass;
            try {
                myClass = (Class<? extends Column>) Class.forName(myValues[1]);
            } catch (ClassNotFoundException e) {
                return false;
            }
            Column myNewColumn;
            try {
                myNewColumn = myClass.getConstructor(String.class).newInstance(myValues[0]);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                return false;
            }
            myColumns.add(myNewColumn);
        }
        return true;
    }
}
