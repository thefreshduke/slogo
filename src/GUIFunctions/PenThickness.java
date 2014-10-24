package GUIFunctions;

import View.Pen;
import View.SingleGrid;

public class PenThickness extends GridFunction{
	private Number myThickness=1;
	public PenThickness(SingleGrid grid){
		myGrid=grid;
	}
	public void doAction(){
		for (Pen myP: myGrid.getActivePens()){
			myP.changeThickness(myThickness);
		}
	}
	public void setMyThickness(Number thickness){
		myThickness=thickness.intValue();
	}
	@Override
	public void doAction(Number newVal) {
		myThickness=newVal;
		for (Pen myP: myGrid.getActivePens()){
			myP.changeThickness(myThickness);
		}
	}
}
