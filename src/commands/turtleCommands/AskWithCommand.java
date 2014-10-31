package commands.turtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.BaseTurtleContainer;
import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class AskWithCommand extends TurtleCommand {

    private BaseCommand myCondition;
    private BaseCommand myInternalCommand;

    public AskWithCommand (String userInput, boolean isExpression)
            throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected int getExpressionCount () {
        return 0;
    }

    @Override
    protected void parseArguments(String userInput) throws BackendException {
        String[] splitConditionCommand = splitByInnerListCommand(userInput.trim());
        myCondition = CommandFactory.createCommand(splitConditionCommand[0], true);
        String[] splitCommandLeftover = splitByInnerListCommand(splitConditionCommand[1]);
        myInternalCommand = CommandFactory.createCommand(splitCommandLeftover[0], false);
        setLeftoverCommands(splitCommandLeftover[1]);
    }

    @Override
    protected double onExecute() throws BackendException {
        BaseTurtleContainer turtleContainer = getTurtleContainer();
        Collection<Integer> oldActiveTurtles = turtleContainer.getActiveTurtlesByID();
        Collection<Integer> allTurtles = turtleContainer.getAllTurtlesByID();
        Collection<Integer> conditionMetTurtles = new LinkedHashSet<>();
        for(int turtleID : allTurtles){
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(turtleID);
            turtleContainer.hardSetActiveTurtles(idList);
            double conditionResult = myCondition.execute();
            if(conditionResult != 0){
                conditionMetTurtles.add(turtleID);
            }
        }
        turtleContainer.hardSetActiveTurtles(conditionMetTurtles);
        double result = myInternalCommand.execute();
        turtleContainer.hardSetActiveTurtles(oldActiveTurtles);
        return result;
    }
}
