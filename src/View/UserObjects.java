package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface UserObjects {

    /**
     * Creates an object on the GUI that the user can interact with
     * 
     * @param handler
     *            ActionEvent for the object to react to
     */
    void addEvent (EventHandler<ActionEvent> handler);

}
