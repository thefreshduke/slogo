package GUIFunctions;

import java.util.List;

public interface GUIFunction {
	public void doAction();
	public void doAction(List<? extends Number> newVal);
}
