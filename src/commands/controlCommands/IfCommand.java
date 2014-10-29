package commands.controlCommands;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class IfCommand extends ControlCommand {

    private BaseCommand myExpression;
    private BaseCommand myInternalCommand;

    public IfCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        double returnValue = 0;
        double expressionResult = myExpression.execute();
        if (expressionResult != 0) {
            returnValue = myInternalCommand.execute();
        }
        else {
            returnValue = expressionResult;
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        myExpression = CommandFactory.createCommand(userInput, true);
        String leftOver = new String(myExpression.getLeftoverString().trim());
        String[] splitCommand = splitByInnerListCommand(leftOver);
        String innerCommand = splitCommand[0];
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        String outerString = splitCommand[1];
        setLeftoverCommands(outerString);
    }

}
