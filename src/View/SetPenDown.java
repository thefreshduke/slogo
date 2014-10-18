package View;

import java.util.Collection;

public class SetPenDown extends GUIFunction{
	Collection<Pen> myPen;
	public SetPenDown(Collection<Pen> myPens){
		myPen=myPens;
	}
	public void doAction(){
		for (Pen myP: myPen){
			myP.setPenDown(true);
		}
	}
	
}
