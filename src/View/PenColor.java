package View;

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
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doAction(String myColor) {
		System.out.println("HEY");
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			System.out.println(p);
			p.setColor(myColor);
		}
	}

}
