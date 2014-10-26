package View;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GUIFunctions.GridFunction;

public class SetPalette extends GridFunction{
	private ColorSelection myColors;
	public SetPalette(ColorSelection colors){
		myColors=colors;
	}


	@Override
	public void doAction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void doAction(List<? extends Number> newVal) {
		Iterator<Number> myIterator=(Iterator<Number>) newVal.iterator();
		String myColor="";
		if (newVal.size()!=0){
			int index=myIterator.next().intValue();
			while(myIterator.hasNext()){
				myColor+=Integer.toHexString(myIterator.next().intValue())+"";
			}
			if (myColor.length()!=6){
				myColor+="00";
			}
			myColors.setColor(index, myColor);
		}
	}
}