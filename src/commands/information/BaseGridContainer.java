package commands.information;

import java.util.Collection;
import java.util.List;

import turtle.Turtle;
import View.Grid;

public abstract class BaseGridContainer implements IInformationContainer {

    public abstract void updateDisplayOptions (String guiCommand);

    public abstract void updateDisplayOptions (String guiCommand, List<Double> parameters);

    public abstract void addGrid (Grid grid, boolean isActive);

    public abstract void setGridAsActive (int gridID);

    public abstract void removeGrid (int gridID);

    public abstract Collection<Grid> getActiveGrids ();

    public abstract void update (Collection<Turtle> collection);
}
