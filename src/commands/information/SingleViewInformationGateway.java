package commands.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;

public class SingleViewInformationGateway implements IInformationGateway {

    private static final String INVALID_CONTAINER_TYPE_ERROR = "Invalid container type";
    private SingleActiveGridContainer myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    private MapBasedVariableContainer myVariableContainer;

    public SingleViewInformationGateway(){
        myGridContainer = new SingleActiveGridContainer();
        myGridToTurtlesMap = new HashMap<>();
        myVariableContainer = new MapBasedVariableContainer();
    }

    public SingleViewInformationGateway(Grid grid, Turtle turtle){
        this();
        myGridContainer.addGrid(grid, true);
        BaseTurtleContainer turtleContainer = new SingleViewTurtleContainer(turtle);
        myGridToTurtlesMap.put(grid.getID(), turtleContainer);
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
            BaseTurtleContainer turtleContainer = myGridToTurtlesMap.get(activeGridID);
            if(turtleContainer == null){
                turtleContainer = new SingleViewTurtleContainer();
                myGridToTurtlesMap.put(activeGridID, turtleContainer);
            }
            return turtleContainer;
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