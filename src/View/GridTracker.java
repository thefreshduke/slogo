package View;

import java.util.HashSet;

public class GridTracker extends HashSet<Grid> {
    SingleGrid myActiveGrid;

    public GridTracker () {

    }

    public SingleGrid getActiveGrid () {
        return myActiveGrid;
    }

    public void setActiveGrid (SingleGrid newActive) {
        if (!this.contains(newActive)) {
            this.add(newActive);
        }

        myActiveGrid = (SingleGrid) newActive;
    }
}
