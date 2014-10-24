package GUIFunctions;

import java.util.HashMap;

import View.Pen;
import View.PenMenu;
import View.SingleGrid;

public class PenStyle extends PenMenu{
	private HashMap<String, BorderStyle> myStyleMap;
	private String myStyle;

	public PenStyle(SingleGrid grid, String whichStyle) {
		myGrid=grid;
		myStyleMap=new HashMap<String,BorderStyle>();
		myStyle=whichStyle;
		makeMap();
		
	}
	public void doAction(){
		for (Pen p: myGrid.getActivePens()){
			p.setBorderStyle(myStyleMap.get(myStyle));
		}
	}

	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	public void makeMap(){
		myStyleMap.put("Dotted", new DottedBorderStyle());
		myStyleMap.put("Solid", new SolidBorderStyle());
		myStyleMap.put("Dashed", new DashedBorderStyle());
	}
}
