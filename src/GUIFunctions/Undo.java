package GUIFunctions;

import java.util.Collection;
import java.util.List;

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
		allGrids.getActiveGrid().update(allGrids.getActiveGrid().getActiveTurtles());
	}


	private void undoLine(Turtle t){
		allGrids.getActiveGrid().getChildren().remove(t.getPen().undo());
		//this.getChildren().remove();
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}

}
