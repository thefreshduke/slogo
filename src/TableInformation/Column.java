package TableInformation;

import GUIFunctions.EditingCell;
import turtle.Turtle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;


public abstract class Column<G> extends TableColumn<Turtle, G>{
	public Column(String s){
		setText(s);
		makeColumn();
		
	}
	private void makeColumn(){
		this.setCellValueFactory(new Callback<CellDataFeatures<Turtle, G>, ObservableValue<G>>() {
			@Override
			public ObservableValue<G> call(
					CellDataFeatures<Turtle, G> myData) {
				return doFunction(myData);
			}
		});
	}
	protected Callback<TableColumn<Turtle, G>, TableCell<Turtle, G>> makeCellFactory(){
		Callback<TableColumn<Turtle, G>, TableCell<Turtle, G>> editFactory=new Callback<TableColumn<Turtle, G>, TableCell<Turtle, G>>(){

			@Override
			public TableCell call(TableColumn myColumn) {
				return makeEditingCell();
			}
			
		};	
		return editFactory;
	}
	public abstract void doEditingFunction (CellEditEvent<Turtle, G> myData , Turtle myTurtle);
	protected abstract EditingCell<Turtle, G> makeEditingCell();
	protected abstract ReadOnlyObjectWrapper<G> doFunction(CellDataFeatures<Turtle, G> myData);
}
