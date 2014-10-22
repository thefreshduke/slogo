package View;

import java.util.Collection;

public class SetPenUp implements GUIFunction{
	SingleGrid myGrid;
	public SetPenUp(SingleGrid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
	
		for (Pen p: myGrid.getActivePens()){
			p.setPenDown(false);
		}
		
	}
}
