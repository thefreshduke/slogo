package View;
import GUIFunctions.ColorFunction;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
public  class ColorLabel extends Button {
	private boolean isActive;
	private ColorFunction myFunction;
	private int myEffectSize=2;
	private final int myTextSize=10;
	public ColorLabel(double x, double y, double width, double height, String myName,ColorFunction myGUIFunction){
		this.setText(myName);
		this.setPrefSize(width, height);
		relocate(x, y);
		isActive=false;
		this.addEvent();
		myFunction=myGUIFunction;
		setStyle(myTextSize);
	}
	private void addEvent(){
		this.setOnMouseClicked(event->setActive(!isActive));
	}
	private void setActive(boolean active){
		isActive=active;
		if (isActive){
			this.setStyle(myTextSize+myEffectSize);
		}
		else 
			this.setStyle(myTextSize-myEffectSize);
	}
	public void isActive(String color){
		if (isActive){
			myFunction.doAction(color);
		}
	}
	public void setStyle(int text){
		setTextFill(Paint.valueOf("WHITE"));
		setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE; -fx-font-size:"+text);
	}
}
