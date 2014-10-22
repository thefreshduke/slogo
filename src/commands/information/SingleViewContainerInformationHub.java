package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import backendExceptions.BackendException;
import View.Grid;

public class SingleViewContainerInformationHub extends BaseCommandInformationHub {

    private GridSet myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    //private  myVariableContainer;
    
    public SingleViewContainerInformationHub(Grid grid){
        myGridContainer = new GridSet();
    }
    
    @Override
    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) throws BackendException {
    	if(BaseTurtleContainer.class.isAssignableFrom(containerType)){
    		ArrayList<Grid> grids = (ArrayList<Grid>)myGridContainer.getActiveGrids();
            if(grids.size() != 1){
            	throw new BackendException(null, "There can only be one active grid");
            }
            Grid activeGrid = grids.get(0);
            Integer activeGridID = -1000;//activeGrid.getID();
            return myGridToTurtlesMap.get(activeGridID);
    	}
    	if(BaseGridContainer.class.isAssignableFrom(containerType)){
    		return myGridContainer;
    	}
        
        return null;		
    }

}
