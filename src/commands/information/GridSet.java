package commands.information;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import View.Grid;

public class GridSet extends BaseGridContainer{

    private ArrayList<Grid> myGridList;
    private Grid myActiveGrid;
    
    public GridSet(){
        this(new ArrayList<Grid>());
    }
    
    public GridSet (Collection<Grid> initialGrids) {
        super(initialGrids);
        myGridList = new ArrayList<>(initialGrids);
        if(myGridList.size() > 0){
            myActiveGrid = myGridList.get(0);
        }
    }

    @Override
    public void addGrid (Grid grid, boolean isActive) {
        myGridList.add(grid);
        if(isActive){
            myActiveGrid = grid;
        }
    }

    public int getActiveGridID(){
    	return -100;//myActiveGrid.getId();
    }
    
    @Override
    public void setGridAsActive (int gridID) {
        for(Grid grid : myGridList){
            if(/*grid.getID()*/ -1000 == gridID){
                myActiveGrid = grid;
            }
        }
    }

    @Override
    public void removeGrid (int gridID) {
        for(int i = 0; i< myGridList.size(); i++){
            if(/*grid.getID()*/ -1000 == gridID){
                myGridList.remove(i);
            }
        }
    }
    
    
}
