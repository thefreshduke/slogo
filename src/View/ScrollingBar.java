package View;


import GUIFunctions.GUIFunction;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;

public abstract class ScrollingBar extends ScrollBar implements UserObjects{
	protected int myX;
	protected int myY;
	protected int myHeight;
	protected int myWidth;
	protected GUIFunction myFunction;
	
	public ScrollingBar(String myLabel, int x, int y, int height, int width, int min, int max, int value, GUIFunction myFunction){
		makeScrollingBar(min, max, value);
		makeLabel(myLabel);
		myX=x;
		myY=y;
		myHeight=height;
		myWidth=width;
	}

	private void makeScrollingBar(int min, int max, int value){
		this.setMin(min);
		this.setMax(max);
		this.setValue(value);
		this.relocate(myX, myY);
	}
	private void makeLabel(String s){
		Label myLabel=new Label(s);
		myLabel.relocate(myX+myWidth/2, myY+myHeight);
		myLabel.setPrefSize(myHeight/2, myWidth/2);
	}
	
	
	 
	
	

}
