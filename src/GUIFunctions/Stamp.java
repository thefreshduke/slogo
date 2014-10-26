package GUIFunctions;

import java.util.List;

import View.GridTracker;
import View.SingleGrid;
import turtle.Turtle;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Stamp extends BottomFunctions {
	public Stamp(GridTracker grid){
		allGrids=grid;
	}

	@Override
	public void doAction() {
		ImageView newImageView=new ImageView();
		for (Turtle turtle: allGrids.getActiveGrid().getActiveTurtles()){
			StampImage myStamp=new StampImage(turtle);
			allGrids.getActiveGrid().getChildren().add(myStamp);
		}
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub

	}
	
}
