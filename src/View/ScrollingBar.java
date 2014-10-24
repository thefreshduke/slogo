package View;


import GUIFunctions.GUIFunction;
import GUIFunctions.PenThickness;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;

public abstract class ScrollingBar extends Group implements UserObjects{
	protected int myX;
	protected int myY;
	protected int myHeight;
	protected int myWidth;
	protected GUIFunction myFunction;
	protected Slider myBar;
	
	
	public ScrollingBar(String myLabel, int x, int y, int height, int width, int min, int max, int value, GUIFunction myFunction){
		myX=x;
		myY=y;
		myHeight=height;
		myWidth=width;
		makeScrollingBar(min, max, value);
		makeLabel(myLabel);
	}

	private void makeScrollingBar(int min, int max, int value){
		myBar=new Slider();
		myBar.setMin(min);
		myBar.setMax(max);
		myBar.setValue(value);
		myBar.relocate(myX, myY);
		myBar.setPrefSize(myHeight, myWidth);
		this.getChildren().add(myBar);
	}
	private void makeLabel(String s){
		Label myLabel=new Label(s);
		myLabel.relocate(myX+myWidth/2, myY+myHeight+10);
		myLabel.setPrefSize(100, 100);
		this.getChildren().add(myLabel);
	}
	
	
	 
	
	

}
