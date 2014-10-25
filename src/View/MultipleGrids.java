package View;

import java.util.HashMap;

public class MultipleGrids {
    private HashMap<Grid, Boolean> myGrids = new HashMap<Grid, Boolean>();

    public MultipleGrids () {
    }

    public void addGrid (Grid grid) {
        clearActive();
        if (!myGrids.containsKey(grid)) {
            myGrids.put(grid, true);
        }

    }

    public void setActive (Grid g) {
        clearActive();
        myGrids.put(g, true);
    }

    public void clearActive () {
        for (Grid g : myGrids.keySet()) {
            myGrids.put(g, false);
        }
    }
}
