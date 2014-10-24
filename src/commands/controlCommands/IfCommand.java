package commands.controlCommands;

import java.util.Collection;
import java.util.Set;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.IInformationContainer;
import commands.information.BaseVariableContainer;
import backendExceptions.BackendException;

public class IfCommand extends ControlCommand{
    
    private BaseCommand myExpression;
    private BaseCommand myInternalCommand;
    
    public IfCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute() throws BackendException {
        double returnValue = 0;
        double expressionResult = myExpression.execute();
        if(expressionResult != 0){
            returnValue = myInternalCommand.execute();
        }
        else{
            returnValue = expressionResult;
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) {
        myExpression = CommandFactory.createCommand(userInput, true);
        String leftOver = new String(myExpression.getLeftoverString().trim());
        String[] splitCommand = splitByInnerListCommand(leftOver);
        String innerCommand = splitCommand[0];
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        String outerString = splitCommand[1];
        setLeftoverCommands(outerString);
    }
}
