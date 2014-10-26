package View;

import turtle.Turtle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TabsOfGrids extends TabPane{
	private EventHandler myEvent;

	public TabsOfGrids(){
		setPrefSize(800, 100);
		relocate(200, 25);
	}
	public SingleGrid getActiveGrid(){
		for (Tab myTab: this.getTabs()){
			if (myTab.isSelected()){
				return (SingleGrid)myTab.getContent();
			}
		}
		return null;
	}
	public void addTab(String gridTitle, SingleGrid grid){
		Tab myNewTab=new Tab();
		this.setPrefSize(grid.getMyWidth(), grid.getMyHeight());
		myNewTab.setText(gridTitle);
		myNewTab.setContent(grid);
		getTabs().add(myNewTab);
		setUpKeyBoardHandler(myNewTab);
		this.setGridTabsFocused(myNewTab);
		disableTabPanes(myNewTab);
		
	}
	public void disableTabPanes(Tab myTab){
		myTab.getTabPane().addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				arg0.consume();
				
			}
			
		});
	
	}
	public void setUpKeyBoardHandler(Tab myTab){
		SingleGrid myGrid=(SingleGrid) myTab.getContent();
		
		myEvent=new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				for (Turtle t: myGrid.getActiveTurtles()){
					t.move(e.getCode());
				}
				myGrid.keyUpdate();
				
			}
			
		};
		addEventHandler(KeyEvent.KEY_PRESSED, myEvent);
	}

	
	public void setGridTabsFocused(Tab myTab){
		SingleGrid myGrid=(SingleGrid) myTab.getContent();
		myGrid.focusedProperty().addListener(new ChangeListener <Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue()){
					myGrid.addEventHandler(KeyEvent.KEY_PRESSED, myEvent);
				}
				else{
					myGrid.removeEventHandler(KeyEvent.KEY_PRESSED, myEvent);
				}
		}});
		
	}
	
}
