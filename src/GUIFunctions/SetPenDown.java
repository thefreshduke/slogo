package GUIFunctions;

import java.util.Collection;

import View.GUIFunction;
import View.Pen;
import View.SingleGrid;

public class SetPenDown implements GUIFunction{
	private SingleGrid myGrid;
	public SetPenDown(SingleGrid grid){
		myGrid=grid;
	}
	public void doAction(){
		for (Pen myP: myGrid.getActivePens()){
			myP.setPenDown(true);
		}
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	
}
