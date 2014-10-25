package GUIFunctions;

import View.GridTracker;
import View.SingleGrid;

public class ClearFunction extends BottomFunctions{
	public ClearFunction(GridTracker grid){
		allGrids=grid;
	}
	@Override
	public void doAction() {
		allGrids.getActiveGrid().getChildren().clear();
		allGrids.getActiveGrid().getActiveTurtles().clear();
		allGrids.getActiveGrid().getAllTurtles().clear();
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}

}
