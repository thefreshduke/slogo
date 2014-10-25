package GUIFunctions;

import java.util.Collection;

import View.GridTracker;
import View.SingleGrid;
import turtle.Turtle;

public class Undo extends BottomFunctions{
	public Undo(GridTracker grid){
		allGrids=grid;
	}

	@Override
	public void doAction() {
		for (Turtle t: allGrids.getActiveGrid().getActiveTurtles()){
			t.undo();
			allGrids.getActiveGrid().moveTurtle(t);
			this.undoLine(t);

		}
	}


	private void undoLine(Turtle t){
		allGrids.getActiveGrid().getChildren().remove(t.getPen().undo());
		//this.getChildren().remove();
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}

}
