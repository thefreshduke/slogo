package View;

import turtle.Turtle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TabsOfGrids extends TabPane{
	private EventHandler myEvent;
	public TabsOfGrids(){
		setPrefSize(800, 100);
		relocate(200, 30);
		
		
	}
	public void addTab(String gridTitle, SingleGrid grid){
		Tab myNewTab=new Tab();
		this.setPrefSize(grid.myWidth, grid.myHeight);
		myNewTab.setText(gridTitle);
		myNewTab.setContent(grid);
		getTabs().add(myNewTab);
		setUpKeyBoardHandler(grid);
	}
	
	public void setUpKeyBoardHandler(SingleGrid grid){
		myEvent=new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				for (Turtle t: grid.getActiveTurtles()){
					t.move(e.getCode());
				}
				grid.keyUpdate();
				
			}
		};
		addEventHandler(KeyEvent.KEY_PRESSED, myEvent);
	}
	
	public void setGridTabsFocused(Node o){
		o.focusedProperty().addListener(new ChangeListener <Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {  
				if (!newValue.booleanValue()){
					o.addEventHandler(KeyEvent.KEY_PRESSED, myEvent);
				}
				else{
					o.removeEventHandler(KeyEvent.KEY_PRESSED, myEvent);
				}
		}});
		
	}
}
