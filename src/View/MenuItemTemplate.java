package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuItemTemplate extends MenuItem implements UserObjects{
	
	public MenuItemTemplate(String s, EventHandler<ActionEvent> handler){
		super(s);
		this.setOnAction(handler);
		
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		// TODO Auto-generated method stub
		
	}
}
