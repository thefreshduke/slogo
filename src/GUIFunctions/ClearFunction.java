package GUIFunctions;

import View.SingleGrid;

public class ClearFunction extends BottomFunctions{
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
