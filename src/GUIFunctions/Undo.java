package GUIFunctions;

import java.util.Collection;

import View.GridTracker;
import View.SingleGrid;
import turtle.Turtle;

public class Undo extends BottomFunctions{
	public Undo(GridTracker grid){
		myGrid=grid.getActiveGrid();
	}

	@Override
	public void doAction() {
		for (Turtle t: myGrid.getActiveTurtles()){
			t.undo();
			myGrid.moveTurtle(t);
			this.undoLine(t);

		}
	}


	private void undoLine(Turtle t){
		myGrid.getChildren().remove(t.getPen().undo());
		//this.getChildren().remove();
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}

}
