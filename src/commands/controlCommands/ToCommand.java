// This entire file is part of my masterpiece.
// Duke Kim

package commands.controlCommands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import backendExceptions.BackendException;
import commands.ControlCommand;
import commands.information.BaseUserDefinedContainer;


/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ToCommand extends ControlCommand {

    private static final String VARIABLE_INDICATOR = "variable";
    private static final String INVALID_ERROR_MESSAGE = "Invalid variable list for To command";
    private static final String REPEATED_VARIABLE_ERROR_MESSAGE = "Variable repeated in To command";
    private static final int EVEN_NUMBER_CHECKER = 2;
    private static final String EMPTY_STRING = "";
    private static final int VALID_INNER_ARGUMENTS_LENGTH = 2;
    private static final String INSUFFICIENT_COMMANDS_ENTERED = "Insufficient commands entered";

    private String myCommandName;
    private String myInternalCommand;
    private String[] myVariables;

    private List<String> myVariableList;
    private Set<String> myTempRepeatChecker;

    public ToCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double executeControl (BaseUserDefinedContainer userDefinedContainer)
                                                                                   throws BackendException {
        userDefinedContainer.addNewCommand(myCommandName, myInternalCommand, myVariables);
        return 1;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {

        String[] innerArguments = userInput.trim().split(COMMAND_SEPARATOR, 2);
        if (innerArguments.length < VALID_INNER_ARGUMENTS_LENGTH) { throw new BackendException(
                                                                                               null,
                                                                                               INSUFFICIENT_COMMANDS_ENTERED); }
        myCommandName = innerArguments[0];

        String innerListCommands = innerArguments[1].trim();
        String[] splitString = splitByInnerListCommand(innerListCommands);
        String variables = splitString[0];
        if (!variables.equals(EMPTY_STRING)) {
            String[] unfilteredVariableList = variables.split(COMMAND_SEPARATOR);
            if (!isEven(unfilteredVariableList.length)) { throw new BackendException(null,
                                                                                     INVALID_ERROR_MESSAGE); }
            myTempRepeatChecker = new HashSet<>();
            myVariableList = new ArrayList<>();
            addWordsToVariablesCollection(unfilteredVariableList);
            myVariables = myVariableList.toArray(new String[myVariableList.size()]);
        }
        else {
            myVariables = new String[0];
        }
        String[] secondSplitString = splitByInnerListCommand(splitString[1]);
        myInternalCommand = secondSplitString[0];
        setLeftoverCommands(secondSplitString[1]);
    }

    private void addWordsToVariablesCollection (String[] unfilteredVariableList)
                                                                                throws BackendException {
        for (int i = 0; i < unfilteredVariableList.length; i++) {
            String word = unfilteredVariableList[i].trim();
            if (isEven(i) && !word.equals(VARIABLE_INDICATOR)) {
                throw new BackendException(null, INVALID_ERROR_MESSAGE);
            }
            else if (!isEven(i)) {
                if (myTempRepeatChecker.contains(word)) { throw new BackendException(null,
                                                                                     REPEATED_VARIABLE_ERROR_MESSAGE); }
                myVariableList.add(word);
                myTempRepeatChecker.add(word);
            }
        }
    }

    private boolean isEven (int number) {
        return number % EVEN_NUMBER_CHECKER == 0;
    }
}
