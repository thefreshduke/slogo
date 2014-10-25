package View;

import java.util.ArrayList;
import java.util.List;

import GUIFunctions.GUIFunction;
import GUIFunctions.PenThickness;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyEvent;

public class PenScrollingBar extends ScrollingBar{

	public PenScrollingBar(String myLabel, int x, int y, int height, int width,
			int min, int max, int value, GUIFunction myFunction) {
		super(myLabel, x, y, height, width, min, max, value, myFunction);
		setFocus();
		// TODO Auto-generated constructor stub
	}
	public PenScrollingBar(String myLabel, int x, int y, GUIFunction myFunction){
		super(myLabel, x, y, 100, 20, 1, 20, 10, myFunction);
		setFocus();
		
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
	}
	private ChangeListener<Number> addEvent() {
		ChangeListener<Number> myListener=new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> ov,Number oldVal, Number newVal) {
				List<Number> myNewValue=new ArrayList<Number>();
				myNewValue.add(newVal);
				myFunction.doAction(myNewValue);
			}
		};
		return myListener;
	}
	
	private void setFocus(){
		myBar.focusedProperty().addListener(new ChangeListener<Boolean> (){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {  
				if (newValue.booleanValue()){
					System.out.println("GR");
					myBar.valueProperty().addListener(addEvent());
				}
				else{
					myBar.valueProperty().removeListener(addEvent());
				}
			}
	
			
		});
	}



}
