package GUIFunctions;

import java.util.Collection;

import View.SingleGrid;
import turtle.Turtle;

public class Undo extends BottomFunctions{
	public Undo(SingleGrid grid){
		myGrid=grid;
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
