package View;

import java.util.Collection;

import turtle.Turtle;

public class Undo implements GUIFunction{
	Grid myGrid;
	public Undo(Grid grid){
		myGrid=grid;
	}

	@Override
	public void doAction() {
		for (Turtle t: myGrid.getActiveTurtles()){
			t.undo();
			System.out.println(t.getXPos()+""+t.getYPos());
			myGrid.moveTurtle(t);
			this.undoLine(t);

		}
	}


	private void undoLine(Turtle t){
		myGrid.getChildren().remove(t.getPen().undo());
		//this.getChildren().remove();
	}

}
