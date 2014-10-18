package communicator;

import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class SlogoModel {
	private Turtle myTurtle;

	protected void initializeModel () {
		Image image = new Image("bowser.png");
		myTurtle = new Turtle(new Position(0, 0), image);
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
	}	

	public void hardSetTurtle (double x, double y, double orientationAngle) {
		myTurtle.setXPos(x);
		myTurtle.setYPos(y);
		myTurtle.setRotation(orientationAngle);
	}

	public Turtle getTurtle() {
		return myTurtle;

	}
}
