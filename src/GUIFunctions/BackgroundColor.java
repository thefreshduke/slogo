package GUIFunctions;

import java.util.List;

import javafx.scene.control.MenuBar;
import View.ColorSelection;
import View.GridTracker;
import View.MenuTemplate;
import View.SingleGrid;


public class BackgroundColor extends ColorFunction{
	ColorSelection myColors;
	public BackgroundColor(GridTracker grid, ColorSelection colors){
		myGrids=grid;
		myColors=colors;
		
	}
	
	public void doAction(List<? extends Number> newVal) {
		myGrids.getActiveGrid().setBackgroundColor(myColors.getAvailableColors().get((int)newVal.get(0)));
		
	}
	public void doAction(String myColor){
		myGrids.getActiveGrid().setBackgroundColor(myColor);
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
