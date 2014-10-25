package commands.information;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import turtle.Turtle;
import View.Grid;

public class SingleActiveGridContainer extends BaseGridContainer {

    // Needs to be list because we are removing by indexes.
    private List<Grid> myGridList;
    private Grid myActiveGrid;

    public SingleActiveGridContainer () {
        myGridList = new ArrayList<>();
    }

    public SingleActiveGridContainer (Grid grid) {
        this();
        myGridList.add(grid);
        myActiveGrid = grid;
    }

    @Override
    public void addGrid (Grid grid, boolean isActive) {
        myGridList.add(grid);
        if (isActive) {
            myActiveGrid = grid;
        }
    }

    @Override
    public void setGridAsActive (int gridID) {
        for (Grid grid : myGridList) {
            if (grid.getID() == gridID) {
                myActiveGrid = grid;
            }
        }
    }

    @Override
    public void removeGrid (int gridID) {
        for (int i = 0; i < myGridList.size(); i++) {
            Grid grid = myGridList.get(i);
            if (grid.getID() == gridID) {
                myGridList.remove(i);
            }
        }
    }

    @Override
    public Collection<Grid> getActiveGrids () {
        return new ArrayList<Grid>(Arrays.asList(myActiveGrid));
    }

    @Override
    public void updateDisplayOptions (String guiCommand) {
        myActiveGrid.updateGUI(guiCommand);
    }

    @Override
    public void updateDisplayOptions (String guiCommand, List<Double> parameters) {
        myActiveGrid.updateGUI(guiCommand, parameters);
    }

    @Override
    public void update (Collection<Turtle> turtles) {
        myActiveGrid.update(turtles);
    }
}