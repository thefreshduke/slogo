package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;

public class MenuTemplate extends Menu implements UserObjects {

    /**
     * Constructor
     * 
     * @param s
     *            String representing the title of the Menu
     * @param handler
     *            Event for which the Menu reacts upon
     */
    public MenuTemplate (String s) {
        super(s);
    }

    public void addMenuItem (String name, EventHandler<ActionEvent> handler) {
        MenuItemTemplate item = new MenuItemTemplate(name, handler);
        this.getItems().add(item);
    }

    @Override
    public void addEvent (EventHandler<ActionEvent> handler) {
        // TODO Auto-generated method stub

    }

}
