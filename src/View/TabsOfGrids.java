package View;

import turtle.Turtle;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TabsOfGrids extends TabPane{

	public TabsOfGrids(){
		this.setPrefSize(800, 100);
		this.relocate(200, 30);
	}
	public void addTab(String gridTitle, SingleGrid grid){
		Tab myNewTab=new Tab();
		this.setPrefSize(grid.myWidth, grid.myHeight);
		myNewTab.setText(gridTitle);
		myNewTab.setContent(grid);
		getTabs().add(myNewTab);
	}
}
