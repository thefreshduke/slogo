package commands.expressionCommands;

import java.util.Collection;
import java.util.Set;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.LogicCommand;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ExpressionCommand extends BaseCommand {

    private static final String INSUFFICIENT_COMMANDS_ENTERED = "Insufficient commands entered";
    private BaseCommand[] myArgumentList;

    public ExpressionCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        return null;
    }

    @Override
    public void setRequiredInformation (Collection<IInformationContainer> containers) {

    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        int argumentCount = getArgumentCount();
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

    protected BaseCommand[] getExpressionList () {
        return myArgumentList;
    }

    @Override
    protected void reset(){}
    
    protected abstract int getArgumentCount ();
}