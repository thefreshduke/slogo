package GUIFunctions;

import java.util.List;

import View.GridTracker;

public class BackgroundColor extends ColorFunction {
    public BackgroundColor (GridTracker grid) {
        myGrids = grid;

    }

    public void doAction (List<Number> newVal) {

    }

    public void doAction (String myColor) {
        myGrids.getActiveGrid().setBackgroundColor(myColor);
    }

    @Override
    public void doAction () {
        // TODO Auto-generated method stub

    }
}
