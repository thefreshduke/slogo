package GUIFunctions;

import View.GridTracker;
import View.Pen;
import View.SingleGrid;

public class PenThickness extends GridFunction{
	private Number myThickness=1;
	public PenThickness(GridTracker grid){
		allGrids=grid;
	}
	public void setMyThickness(Number thickness){
		myThickness=thickness.intValue();
	}
	@Override
	public void doAction(Number newVal) {
		myThickness=newVal;
		for (Pen myP: allGrids.getActiveGrid().getActivePens()){
			myP.changeThickness(myThickness);
		}
	}
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
