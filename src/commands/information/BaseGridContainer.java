package commands.information;

import java.util.Collection;
import View.Grid;

public abstract class BaseGridContainer implements IInformationContainer{

    public BaseGridContainer(Collection<Grid> initialGrids){
    }
    
    public abstract void addGrid(Grid grid, boolean isActive);
    
    public abstract void setGridAsActive(int gridID);
    
    public abstract void removeGrid(int gridID); 
    
}
