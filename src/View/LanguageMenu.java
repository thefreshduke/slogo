package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LanguageMenu extends MenuTemplate {

	public LanguageMenu(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		// TODO Auto-generated method stub
		
	}

	public void addMenuItem(String name, EventHandler<ActionEvent> handler){
		LanguageMenuItem item = new LanguageMenuItem(name, handler);
		this.getItems().add(item);
	}
	
}
