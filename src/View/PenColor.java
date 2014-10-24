package View;

import GUIFunctions.ColorFunction;
import GUIFunctions.GridFunction;

public class PenColor extends ColorFunction{
	SingleGrid myGrid;
	public PenColor(GridTracker grid){
		myGrid=grid.getActiveGrid();
	}
	@Override
	public void doAction() {
		
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doAction(String myColor) {
		myGrid.setBackgroundColor(myColor);
		
	}

}
