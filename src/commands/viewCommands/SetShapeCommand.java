package commands.viewCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class SetShapeCommand extends ViewCommand {
    private static final String SET_SHAPE = "setShape";

    public SetShapeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        double shapeIndex = getExpressionList()[0].execute();
        List<Double>shapeIndexList = new ArrayList<Double>();
        shapeIndexList.add(shapeIndex);
        // Following uncommented line would have been functional, but due to lack of time 
        // method was not functional for shape selection parameter.
        //grid.updateDisplayOptions(SET_SHAPE, shapeIndexList);
        return shapeIndex;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}
