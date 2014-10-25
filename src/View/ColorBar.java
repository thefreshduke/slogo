package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ColorBar extends MenuBar {
		
		private MenuTemplate myMenu;
		public ColorBar(String s){
			myMenu= new MenuTemplate(s);
			getMenus().add(myMenu);
			setPrefSize(150, 25);
		}
		public void addItem (String myName, EventHandler<ActionEvent> myEvent){
			myMenu.addMenuItem(myName, myEvent);
		}
	}
