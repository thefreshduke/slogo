package commands.controlCommands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseVariableContainer;

public class ToCommand extends ControlCommand {
    private String myCommandName;
    private String myInternalCommand;
    private String[] myVariables;
    private List<BaseCommand> myVariableCommandList;
    private static final String VARIABLE_INDICATOR = "variable";
    private static final String INVALID_ERROR_MESSAGE = "Invalid variable list for To command";
    private static final String REPEATED_VARIABLE_ERROR_MESSAGE =
            "Repeated variable name for To command";
    private static final int EVEN_NUMBER_CHECKER = 2;

    public ToCommand (String userInput, boolean isExpression)
            throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseVariableContainer variableContainer = getVariableContainer();
        variableContainer.addNewCommand(myCommandName, myInternalCommand, myVariables);
        return 1;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {

        String[] innerArguments = userInput.trim().split(COMMAND_SEPARATOR, 2);
        if (innerArguments.length < 2) {
            // TODO throw exception
        }
        myCommandName = innerArguments[0];

        String innerListCommands = innerArguments[1].trim();
        String[] splitString = splitByInnerListCommand(innerListCommands);
        String variables = splitString[0];
        if (!variables.equals("")) {
            String[] unfilteredVariableList = variables.split(COMMAND_SEPARATOR);
            if (!isEven(unfilteredVariableList.length)) {
                throw new BackendException(null, INVALID_ERROR_MESSAGE);
            }
            HashSet<String> tempRepeatChecker = new HashSet<>();
            ArrayList<String> variableList = new ArrayList<>();
            for (int i = 0; i < unfilteredVariableList.length; i++) {
                String word = unfilteredVariableList[i].trim();
                if (isEven(i) && !word.equals(VARIABLE_INDICATOR)) {
                    throw new BackendException(null, INVALID_ERROR_MESSAGE);
                }
                else if (!isEven(i)) {
                    if (tempRepeatChecker.contains(word)) {
                        throw new BackendException(null, REPEATED_VARIABLE_ERROR_MESSAGE);
                    }
                    variableList.add(word);
                    tempRepeatChecker.add(word);
                }
            }
            myVariables =  variableList.toArray(new String[variableList.size()]);
        }
        else {
            myVariables = new String[0];
        }
        String[] secondSplitString = splitByInnerListCommand(splitString[1]);
        myInternalCommand = secondSplitString[0];
        setLeftoverCommands(secondSplitString[1]);
    }

    private boolean isEven (int number) {
        return number % EVEN_NUMBER_CHECKER == 0;
    }
}
