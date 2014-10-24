package GUIFunctions;

import javafx.scene.control.MenuBar;
import View.ColorSelection;
import View.GridTracker;
import View.MenuTemplate;
import View.SingleGrid;


public class BackgroundColor extends ColorFunction{
	SingleGrid myGrid;
	public BackgroundColor(GridTracker grid){
		myGrid=grid.getActiveGrid();
		
	}
	
	public void doAction(Number newVal) {
		
	}
	public void doAction(String myColor){
		myGrid.setBackgroundColor(myColor);
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
