package GUIFunctions;

import turtle.Turtle;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class StampImage extends ImageView{
	public StampImage(Turtle myTurtle){
		setImage(myTurtle.getImage());
		setFitWidth(myTurtle.getFitWidth());;
		setPreserveRatio(true);
		setSmooth(true);
		setDisable(true);
		relocate(myTurtle.getXPos(), myTurtle.getYPos());
		addEffect();
		
	}
	private void  addEffect(){
		DropShadow myShadow=new DropShadow();
		myShadow.setRadius(getFitWidth()*.5);
		myShadow.setColor(Color.BLACK);
		setEffect(myShadow);
	}
}
