package GUIFunctions;

import java.util.List;

import View.GridTracker;
import View.Pen;

public class SetPenDown extends BottomFunctions {
    public SetPenDown (GridTracker grid) {
        allGrids = grid;
    }

    public void doAction () {
        for (Pen myP : allGrids.getActiveGrid().getActivePens()) {
            myP.setPenDown(true);
        }
    }

    @Override
    public void doAction (List<Number> newVal) {
        // TODO Auto-generated method stub

    }

}
