package GUIFunctions;

import java.util.List;

import turtle.Turtle;
import View.GridTracker;

public class Undo extends BottomFunctions {
    public Undo (GridTracker grid) {
        allGrids = grid;
    }

    @Override
    public void doAction () {
        for (Turtle t : allGrids.getActiveGrid().getActiveTurtles()) {
            t.undo();
            allGrids.getActiveGrid().moveTurtle(t);
            this.undoLine(t);

        }
        allGrids.getActiveGrid().update(allGrids.getActiveGrid().getActiveTurtles());
    }

    private void undoLine (Turtle t) {
        allGrids.getActiveGrid().getChildren().remove(t.getPen().undo());
        // this.getChildren().remove();
    }

    @Override
    public void doAction (List<Number> newVal) {
        // TODO Auto-generated method stub

    }

}
