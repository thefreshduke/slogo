package GUIFunctions;

import View.GUIFunction;
import View.SingleGrid;

public class ClearFunction implements GUIFunction{
	private SingleGrid myGrid;
	public ClearFunction(SingleGrid myG){
		myGrid=myG;
	}
	@Override
	public void doAction() {
		myGrid.getChildren().clear();
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}

}
