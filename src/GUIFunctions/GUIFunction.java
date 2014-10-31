package GUIFunctions;

import java.util.List;

import javafx.event.EventHandler;

public interface GUIFunction  {
	public void doAction();
	public void doAction(List<? extends Number> newVal);
}
