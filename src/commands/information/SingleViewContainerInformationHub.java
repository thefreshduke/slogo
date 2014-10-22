package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import backendExceptions.BackendException;
import View.Grid;

public class SingleViewContainerInformationHub extends BaseCommandInformationHub {

    private GridSet myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    
    public SingleViewContainerInformationHub(Grid grid){
        myGridContainer = new GridSet();
    }
    
    @Override
    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) throws BackendException {
        ArrayList<Grid> grids = (ArrayList<Grid>)myGridContainer.getActiveGrids();
        if(grids.size() != 1){
        	throw new BackendException(null, "");
        }
        //Grid activeGrid = 
        return null;		
    }

}
