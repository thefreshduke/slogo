package GUIFunctions;

import java.util.List;

import javafx.scene.control.MenuBar;
import View.ColorSelection;
import View.GridTracker;
import View.MenuTemplate;
import View.SingleGrid;


public class BackgroundColor extends ColorFunction{
	public BackgroundColor(GridTracker grid){
		myGrids=grid;
		
	}
	
	public void doAction(List<Number> newVal) {
		
	}
	public void doAction(String myColor){
		myGrids.getActiveGrid().setBackgroundColor(myColor);
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
