package GUIFunctions;

import java.util.List;

import View.GridTracker;
import View.Pen;

public class SetPenUp extends BottomFunctions {
    public SetPenUp (GridTracker grid) {
        allGrids = grid;
    }

    @Override
    public void doAction () {

        for (Pen p : allGrids.getActiveGrid().getActivePens()) {
            p.setPenDown(false);
        }

    }

    @Override
    public void doAction (List<Number> newVal) {
        // TODO Auto-generated method stub

    }
}
