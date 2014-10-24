package View;

import GUIFunctions.GUIFunction;
import GUIFunctions.PenThickness;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollBar;

public class PenScrollingBar extends ScrollingBar{
	
	public PenScrollingBar(String myLabel, int x, int y, int height, int width,
			int min, int max, int value, GUIFunction myFunction) {
		super(myLabel, x, y, height, width, min, max, value, myFunction);
		// TODO Auto-generated constructor stub
	}
	public PenScrollingBar(String myLabel, int x, int y, PenThickness myFunction){
		super(myLabel, x, y, 100, 20, 1, 20, 10, myFunction);
		
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		myBar.valueProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> ov,Number oldVal, Number newVal) {
				myFunction.doAction(newVal);
				
			}
			
		});	
	}

	

}
