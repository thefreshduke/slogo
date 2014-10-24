package View;

import javafx.scene.layout.Pane;

public abstract class Grid extends Pane {
	public int ID;
	
	//need factory with static int
	public void Grid(){
		//ID=(int) (Math.random()*);
	}
	public int getID(){
		return ID;
	}
}
