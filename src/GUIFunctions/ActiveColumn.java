package GUIFunctions;

import javafx.beans.property.ReadOnlyObjectWrapper;
import turtle.Turtle;

public class ActiveColumn extends Column<Boolean> {

    public ActiveColumn (String s) {
        super(s);
    }

    @Override
    public ReadOnlyObjectWrapper<Boolean> doFunction (CellDataFeatures<Turtle, Boolean> myData) {
        return new ReadOnlyObjectWrapper<Boolean>(myData.getValue().isActive());
    }

    @Override
    public void doEditingFunction (
            javafx.scene.control.TableColumn.CellEditEvent<Turtle, Boolean> myData, Turtle myTurtle) {
        // TODO Auto-generated method stub

    }

    @Override
    protected EditingCell<Turtle, Boolean> makeEditingCell () {
        // TODO Auto-generated method stub
        return null;
    }

}
