package GUIFunctions;

import java.util.List;

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
	public void doAction(List<Number> newVal) {
		myThickness=newVal.get(0);
		for (Pen myP: allGrids.getActiveGrid().getActivePens()){
			myP.changeThickness(myThickness);
		}
	}
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
}
