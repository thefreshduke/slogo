package commands.controlCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.IVariableContainer;
import commands.variableCommands.CreatedCommand;


public class ToCommand extends ControlCommand {
    private String myCommandName;
    private String myInternalCommand;
    private String[] myVariables;
    private List<BaseCommand> myVariableCommandList;
    private static final String VARIABLE_INDICATOR = "variable";    
    public ToCommand (String userInput, boolean isExpression)
                                                             throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double execute () throws BackendException {
    	IVariableContainer variableContainer = getVariableContainer();
        variableContainer.addNewCommand(myCommandName, myInternalCommand, myVariables);
        return 0;
    }

    @Override
    protected void parseArguments (String userInput) {

        String[] innerArguments = userInput.trim().split(COMMAND_SEPARATOR, 2);
        if (innerArguments.length < 2) {
            // TODO throw exception
        }
        myCommandName = innerArguments[0];
        
        String innerListCommands = innerArguments[1].trim();
        String[] splitString = splitByInnerListCommand(innerListCommands);
        String variables = splitString[0];
        
        String[] unfilteredVariableList = variables.split(COMMAND_SEPARATOR);
        ArrayList<String> variableList = new ArrayList<>();
        for(int i = 0; i < unfilteredVariableList.length; i++){
            String word = unfilteredVariableList[i].trim();
            if(i%2 ==0 && !word.equals(VARIABLE_INDICATOR)){
                //TODO: throw
            }
            else if (i%2 == 1){
                
            }
        }
        
        String[] secondSplitString = splitByInnerListCommand(splitString[1]);
        myInternalCommand = secondSplitString[0];
        
        setLeftoverCommands(secondSplitString[1]);
    }
}
