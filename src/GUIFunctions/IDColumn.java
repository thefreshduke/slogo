package GUIFunctions;

import javafx.beans.property.ReadOnlyObjectWrapper;
import turtle.Turtle;


public class IDColumn extends Column<Integer>{

	public IDColumn(String s) {
		super(s);
	}

	@Override
	public ReadOnlyObjectWrapper<Integer> doFunction(CellDataFeatures<Turtle, Integer> myData) {
		return new ReadOnlyObjectWrapper<Integer>(myData.getValue().getID());
	}

	@Override
	public void doEditingFunction(
			javafx.scene.control.TableColumn.CellEditEvent<Turtle, Integer> myData,
			Turtle myTurtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected EditingCell<Turtle, Integer> makeEditingCell() {
		// TODO Auto-generated method stub
		return null;
	}
}
