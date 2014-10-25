package View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GUIFunctions.GridFunction;

public class SetPallete extends GridFunction{
	ColorSelection myColors;
	public SetPallete(ColorSelection colors){
		myColors=colors;
	}


	@Override
	public void doAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doAction(List<Number> newVal) {
		int myIndex=0;
		String myColor="";
		Iterator<Number> it=newVal.iterator();
		if (newVal.size()!=0){
			myIndex=(int)it.next();
		}
		while (it.hasNext()){
			myColor=it.next()+"";
		}
		myColors.setColor(myIndex, myColor);
			
		
	}
	}