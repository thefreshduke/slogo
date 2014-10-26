package View;

import java.util.List;

import GUIFunctions.ColorFunction;
import GUIFunctions.GridFunction;

public class PenColor extends ColorFunction{
	ColorSelection myColors;
	public PenColor(GridTracker grid, ColorSelection color){
		allGrids=grid;
		myColors=color;
	}
	@Override
	public void doAction() {
		
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			p.setColor(myColors.getAvailableColors().get(newVal.get(0).intValue()));
		}
		
	}
	@Override
	public void doAction(String myColor) {
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			p.setColor(myColor);
		}
	}

}
