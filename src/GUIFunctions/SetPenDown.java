package GUIFunctions;

import java.util.Collection;

import View.GridTracker;
import View.Pen;
import View.SingleGrid;

public class SetPenDown extends BottomFunctions{
	public SetPenDown(GridTracker grid){
		allGrids=grid;
	}
	public void doAction(){
		for (Pen myP: allGrids.getActiveGrid().getActivePens()){
			myP.setPenDown(true);
		}
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	
}
