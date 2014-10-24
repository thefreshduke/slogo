package GUIFunctions;

import View.Pen;
import View.SingleGrid;

public class PenStyle extends GridFunction{
	public PenStyle(SingleGrid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
		for (Pen activePen: myGrid.getActivePens()){
			//activePen.changeThickness(myThickness);
		}
		
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	
		
}
