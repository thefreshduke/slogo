package View;

import java.util.List;

import GUIFunctions.ColorFunction;
import GUIFunctions.GridFunction;

public class PenColor extends ColorFunction{
	public PenColor(GridTracker grid){
		allGrids=grid;
	}
	@Override
	public void doAction() {
		
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doAction(String myColor) {
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			p.setColor(myColor);
		}
	}

}
