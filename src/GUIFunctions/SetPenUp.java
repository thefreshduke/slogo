package GUIFunctions;

import java.util.Collection;

import View.GridTracker;
import View.Pen;
import View.SingleGrid;

public class SetPenUp extends BottomFunctions{
	public SetPenUp(GridTracker grid){
		allGrids=grid;
	}

	@Override
	public void doAction() {
	
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			p.setPenDown(false);
		}
		
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
}
