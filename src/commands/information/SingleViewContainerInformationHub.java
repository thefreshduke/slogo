package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;

public class SingleViewContainerInformationHub implements ICommandInformationHub {

    private static final String INVALID_CONTAINER_TYPE_ERROR = "Invalid container type";
    private SingleActiveGridContainer myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    private MapBasedVariableContainer myVariableContainer;
    
    public SingleViewContainerInformationHub(Grid grid, Turtle turtle){
        myGridContainer = new SingleActiveGridContainer(grid);
        myGridContainer.addGrid(grid, true);
        myGridToTurtlesMap = new HashMap<>();
        
        myVariableContainer = new MapBasedVariableContainer();
    }
    
    @Override
    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) {
    	if(BaseTurtleContainer.class.isAssignableFrom(containerType)){
    	    ArrayList<Grid> grids = (ArrayList<Grid>)myGridContainer.getActiveGrids();
            if(grids.size() != 1){
            	return null;
            }
            Grid activeGrid = grids.get(0);
            Integer activeGridID = activeGrid.getID();
            return myGridToTurtlesMap.get(activeGridID);
    	}
    	if(BaseGridContainer.class.isAssignableFrom(containerType)){
    	    return myGridContainer;
    	}
    	if(BaseVariableContainer.class.isAssignableFrom(containerType)){
    	    return myVariableContainer;
    	}
        return null;		
    }

    @Override
    public Collection<IInformationContainer> getContainers (Set<Class<? extends IInformationContainer>> containerTypes)
                                                                                                                       throws BackendException {
        ArrayList<IInformationContainer> containerList = new ArrayList<>();
        for(Class<? extends IInformationContainer> containerType : containerTypes){
            IInformationContainer container = getContainer(containerType);
            if(container == null){
                throw new BackendException(null, INVALID_CONTAINER_TYPE_ERROR);
            }
            containerList.add(container);
        }
        return containerList;
    }

}
