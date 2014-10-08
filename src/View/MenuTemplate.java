package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;


public abstract class MenuTemplate extends Menu implements UserObjects{
	
	/**
	 * Constructor
	 * @param s			String representing the title of the Menu
	 * @param handler	Event for which the Menu reacts upon
	 */
	public MenuTemplate(String s, EventHandler<ActionEvent> handler){
		super(s);
	}
	
	public void addMenuItem(String name, EventHandler<ActionEvent> handler, MenuItemTemplate menuItem){
		this.getItems().addAll(menuItem);
	}


	
}
