package commands.information;

import java.util.Map;
import View.Grid;

public class SingleViewContainerInformationHub extends BaseCommandInformationHub {

    private GridSet myGridContainer;
    private Map<Integer, BaseTurtleContainer> myGridToTurtlesMap;
    
    public SingleViewContainerInformationHub(Grid grid){
        myGridContainer = new GridSet();
    }
    
    @Override
    public IInformationContainer getContainer (Class<? extends IInformationContainer> containerType) {
        //Grid grid = myGridContainer.getActiveGridID();
    }

}
