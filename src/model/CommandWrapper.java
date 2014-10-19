package model;

import turtle.Turtle;
import View.Grid;
import View.SlogoView;
import communicator.IVariableContainer;
import communicator.MapBasedVariableContainer;

public class CommandWrapper {
	private Grid myGrid;
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;
	
	public void initializeWrapper(Grid grid, Turtle turtle) {
		setGrid(grid);
		setTurtle(turtle);
		initializeVariableContainer();
	}

	public IVariableContainer getVariableContainer() {
		return myVariableContainer;
	}
	
	public void initializeVariableContainer() {
		myVariableContainer = new MapBasedVariableContainer();
	}
	
	public Turtle getTurtle() {
		return myTurtle;
	}
	
	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public void setGrid(Grid grid) {
		myGrid = grid;
	}
}