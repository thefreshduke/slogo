package View;

import java.util.Collection;

public class SetPenDown implements GUIFunction{
	Grid myGrid;
	public SetPenDown(Grid grid){
		myGrid=grid;
	}
	public void doAction(){
		for (Pen myP: myGrid.getActivePens()){
			myP.setPenDown(true);
		}
	}
	
}
