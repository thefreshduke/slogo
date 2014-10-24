package commands.information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import View.Grid;
import backendExceptions.BackendException;

public class SingleViewContainerInformationHub extends BaseCommandInformationHub {

    private SingleActiveGridContainer myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    private MapBasedVariableContainer myVariableContainer;
    
    public SingleViewContainerInformationHub(Grid grid){
        myGridContainer = new SingleActiveGridContainer();
        myGridToTurtlesMap = new HashMap<>();
        myVariableContainer = new MapBasedVariableContainer();
    }
    
    @Override
    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) throws BackendException {
    	if(BaseTurtleContainer.class.isAssignableFrom(containerType)){
    	    ArrayList<Grid> grids = (ArrayList<Grid>)myGridContainer.getActiveGrids();
            if(grids.size() != 1){
            	throw new BackendException(null, "There can only be one active grid");
            }
            Grid activeGrid = grids.get(0);
            Integer activeGridID = activeGrid.getID();
            return myGridToTurtlesMap.get(activeGridID);
    	}
    	if(BaseGridContainer.class.isAssignableFrom(containerType)){
    	    return myGridContainer;
    	}
    	if(IVariableContainer.class.isAssignableFrom(containerType)){
    	    return myVariableContainer;
    	}
        return null;		
    }

}
