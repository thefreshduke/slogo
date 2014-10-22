package GUIFunctions;

import View.GUIFunction;
import View.Pen;
import View.SingleGrid;

public class PenStyle implements GUIFunction{
	SingleGrid myGrid;
	public PenStyle(SingleGrid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
		for (Pen activePen: myGrid.getActivePens()){
			activePen.changeThickness(activePen);
		}
		
	}
	
		
}
