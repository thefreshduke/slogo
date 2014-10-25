package GUIFunctions;

import turtle.Turtle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn.CellEditEvent;


public class YColumn extends Column<Double>{

	public YColumn(String s) {
		super(s);
		setEditable(true);
		this.setCellFactory(makeCellFactory());
	}

	@Override
	public ReadOnlyObjectWrapper<Double> doFunction(CellDataFeatures<Turtle, Double> myData) {
		return new ReadOnlyObjectWrapper<Double> (myData.getValue().getYPos());
	}
	@Override
	protected EditingCell<Turtle, Double> makeEditingCell() {
		return new EditingTurtleDoubleCell();
	}

	public void doEditingFunction(CellEditEvent<Turtle, Double> myData, Turtle myTurtle) {
		Turtle newPositionTurtle=myData.getTableView().getItems().get(myData.getTablePosition().getRow());
		newPositionTurtle.setYPos(myData.getNewValue());
		
	}



}
