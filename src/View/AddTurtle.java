package View;

import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class AddTurtle implements GUIFunction{
	Grid myGrid;
	public AddTurtle(Grid grid){
		myGrid=grid;
	}
	@Override
	public void doAction() {
		Image image = new Image("bowser.png");
		Turtle myTurtle = new Turtle(new Position(0, 0), image);
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
		myGrid.addTurtle(myTurtle);

	}
}

