package commands.information;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import turtle.Turtle;
import View.Grid;


public class SingleActiveGridContainer extends BaseGridContainer {

    private ArrayList<Grid> myGridList;
    private Grid myActiveGrid;

    public SingleActiveGridContainer () {
        this(new ArrayList<Grid>());
    }

    public SingleActiveGridContainer (Collection<Grid> initialGrids) {
        super(initialGrids);
        myGridList = new ArrayList<>(initialGrids);
        if (myGridList.size() > 0) {
            myActiveGrid = myGridList.get(0);
        }
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
	public void updateDisplayOptions(String guiCommand) {
		myActiveGrid.updateGUI(guiCommand);
		
	}

	@Override
	public void updateDisplayOptions(String guiCommand, double parameter) {
		myActiveGrid.updateGUI(guiCommand, parameter);
	}

	@Override
	public void update(Collection<Turtle> turtles) {
		myActiveGrid.update(turtles);
	}
}
