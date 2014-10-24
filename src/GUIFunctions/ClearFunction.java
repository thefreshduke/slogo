package GUIFunctions;

import View.GridTracker;
import View.SingleGrid;

public class ClearFunction extends BottomFunctions{
	public ClearFunction(GridTracker grid){
		myGrid=grid.getActiveGrid();
	}
	@Override
	public void doAction() {
		myGrid.getChildren().clear();
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}

}
