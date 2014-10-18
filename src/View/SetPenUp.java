package View;

import java.util.Collection;

public class SetPenUp implements GUIFunction{
	Collection<Pen> myPens;
	public SetPenUp(Collection<Pen> myP){
		myPens=myP;
	}

	@Override
	public void doAction() {
		for (Pen p: myPens){
			p.setPenDown(false);
		}
		
	}
}
