package GUIFunctions;

import java.util.HashMap;
import java.util.List;

import View.GridTracker;
import View.Pen;
import View.PenMenu;

public class PenStyle extends PenMenu{
	private HashMap<String, BorderStyle> myStyleMap;
	private String myStyle;

	public PenStyle(GridTracker grid, String whichStyle) {
		allGrids=grid;
		myStyleMap=new HashMap<String,BorderStyle>();
		myStyle=whichStyle;
		makeMap();
		
	}
	public void doAction(){
		for (Pen p: allGrids.getActiveGrid().getActivePens()){
			p.setBorderStyle(myStyleMap.get(myStyle));
		}
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
	public void makeMap(){
		myStyleMap.put("Dotted", new DottedBorderStyle());
		myStyleMap.put("Solid", new SolidBorderStyle());
		myStyleMap.put("Dashed", new DashedBorderStyle());
	}
}
