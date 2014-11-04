// // This entire file is part of my masterpiece.
// Scotty Shaw

package commands.logicCommands;

import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.IInformationContainer;

/**
 * Abstract ExpressionCommand can be extended for every Expression Command.
 * Subclasses are able to override parseArguments() if necessary, but they must
 * each implement getExpressionCount() and onExecute().
 * 
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class LogicCommand extends BaseCommand {

    private static final String INSUFFICIENT_COMMANDS_ENTERED = "Insufficient commands entered";
    private static final String TOO_MANY_CONTAINERS_PROVIDED = "Too many containers provided";
    private static final int NUM_TURTLE_CONTAINERS = 0;
    private BaseCommand[] myArgumentList;

    public LogicCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    /**
     * This method returns null because ExpressionCommands do not use any
     * containers.
     * 
     * @return null
     */
    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        return null;
    }

    /**
     * This method sets the correct number of container types for all
     * ExpressionCommands, which is 0. If any containers are set, this method
     * throws an exception.
     */
    @Override
    public void setRequiredInformation (Collection<IInformationContainer> containers)
            throws BackendException {
        if (containers.size() < NUM_TURTLE_CONTAINERS) {
            throw new BackendException(null, TOO_MANY_CONTAINERS_PROVIDED);
        }
    }

    /**
     * This method sets the parsing algorithm to create BaseCommand objects for
     * each expression by calling getExpressionCount() to ascertain how many
     * expressions each command needs.
     */
    @Override
    protected void parseArguments (String userInput) throws BackendException {
        int argumentCount = getExpressionCount();
        if (argumentCount < 0) {
            throw new BackendException(null, INSUFFICIENT_COMMANDS_ENTERED);
        }
        myArgumentList = new BaseCommand[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            String subInput;
            if (i == 0) {
                subInput = userInput;
            }
            else {
                subInput = myArgumentList[i - 1].getLeftoverString();
            }
            if (!subInput.equals("")) {
                BaseCommand argument = CommandFactory.createCommand(subInput, true);
                myArgumentList[i] = argument;
            }
        }
        setLeftoverCommands(myArgumentList[myArgumentList.length - 1].getLeftoverString());
    }

    /**
     * This method gets the list of expressions that were created as BaseCommand
     * objects. These commands then operate to output doubles.
     * 
     * @return array of BaseCommand expressions
     */
    protected BaseCommand[] getExpressionList () {
        return myArgumentList;
    }

    /**
     * This method gets the number of expressions required by the Expression
     * Command. The number is then used to generate the correct number of
     * expressions for the command. The extending subclasses that specify the
     * number of expressions required must implement this method.
     * 
     * @return int of argument count
     */
    protected abstract int getExpressionCount ();
}