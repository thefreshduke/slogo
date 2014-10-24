package GUIFunctions;

import java.util.Collection;

import View.Pen;
import View.SingleGrid;

public class SetPenUp extends GridFunction{
	public SetPenUp(SingleGrid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
	
		for (Pen p: myGrid.getActivePens()){
			p.setPenDown(false);
		}
		
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
}
