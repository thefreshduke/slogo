package GUIFunctions;

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
			newImageView.setImage(turtle.getImage());
			newImageView.setFitWidth(turtle.getFitWidth());;
			newImageView.setPreserveRatio(true);
			newImageView.setSmooth(true);
			newImageView.setDisable(true);
			newImageView.relocate(100, 200);
			addEffect(newImageView);
			allGrids.getActiveGrid().getChildren().add(newImageView);
		}
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub

	}
	private void  addEffect(ImageView myView){
		DropShadow myShadow=new DropShadow();
		myShadow.setRadius(myView.getFitWidth()*.5);
		myShadow.setColor(Color.BLACK);
		myView.setEffect(myShadow);
	}
}
