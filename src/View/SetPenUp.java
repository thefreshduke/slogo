package View;

import java.util.Collection;

public class SetPenUp implements GUIFunction{
	Grid myGrid;
	public SetPenUp(Grid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
	
		for (Pen p: myGrid.getActivePens()){
			p.setPenDown(false);
		}
		
	}
}
