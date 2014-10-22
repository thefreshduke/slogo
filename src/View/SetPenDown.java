package View;

import java.util.Collection;

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
	
}
