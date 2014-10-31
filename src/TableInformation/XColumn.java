package TableInformation;

import turtle.Turtle;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class XColumn extends Column<Double>{

	public XColumn(String s) {
		super(s);
		setEditable(true);
		this.setCellFactory(makeCellFactory());
	}
	
	@Override
	public ReadOnlyObjectWrapper<Double> doFunction(CellDataFeatures<Turtle, Double> myData) {
		return new ReadOnlyObjectWrapper<Double> (myData.getValue().getXPos());
	}

	@Override
	protected EditingCell<Turtle, Double> makeEditingCell() {
		return new EditingTurtleDoubleCell();
	}

	@Override
	public void doEditingFunction(CellEditEvent<Turtle, Double> myData, Turtle myTurtle) {
		Turtle newPositionTurtle=myData.getTableView().getItems().get(myData.getTablePosition().getRow());
		newPositionTurtle.setXPos(myData.getNewValue());
		
	}

}
