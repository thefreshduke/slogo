// This entire file is part of my masterpiece.
// Duke Kim

package commands;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.Stack;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.information.IInformationContainer;


/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 * 
 *         Abstract class for all commands. All other types of commands will extend the
 *         BaseCommand.
 *
 */
public abstract class BaseCommand implements Serializable {
    private static final String INVALID_INNER_LIST_COMMAND_MESSAGE =
            "Invalid inner list command string";
    private static final long serialVersionUID = 1L;
    private BaseCommand myNextCommand;
    private String myLeftoverString = "";
    private boolean myExpressionFlag;
    protected final String COMMAND_DELIMITER = "\\s+";
    protected static String LIST_COMMAND_INDICATOR = "liststart";
    protected static String LIST_COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
    protected static String VARIABLE_INDICATOR = "variable";

    /**
     * 
     * @param userInput The entire substring of the user input that is past the string used to
     *        identify this specific instance of the command
     * @throws BackendException Any exception while creating the command/parsing.
     */
    public BaseCommand (String userInput, boolean isExpression) throws BackendException {
        myExpressionFlag = isExpression;
        parseArguments(userInput);
    }

    /**
     * Returns the value of onExecute() if there is no next command. If there is a next command,
     * executes that, and then returns the value of the next command's execution instead. Resets the
     * command to its initial state before exiting. Takes care of this behavior because all commands
     * follow this upon execution.
     * 
     * @return Return value of the command
     * @throws BackendException Any exception while executing command.
     */
    public double execute () throws BackendException {
        double result = onExecute();
        if (getNextCommand() != null) { return getNextCommand().execute(); }
        reset();
        return result;
    }

    /**
     * The method to be implemented by all extending classes.
     * 
     * @return
     * @throws BackendException
     */
    protected abstract double onExecute () throws BackendException;

    public abstract Set<Class<? extends IInformationContainer>> getRequiredInformationTypes ();

    public abstract void setRequiredInformation (Collection<IInformationContainer> containers)
                                                                                              throws BackendException;

    private BaseCommand getNextCommand () {
        return myNextCommand;
    }

    protected abstract void parseArguments (String userInput) throws BackendException;

    public String getLeftoverString () {
        return myLeftoverString;
    }

    protected void setLeftoverCommands (String string) throws BackendException {
        if (myExpressionFlag) {
            myLeftoverString = string;
        }
        else if (string != null && string != "") {
            myNextCommand = CommandFactory.createCommand(string, false);
        }
    }

    /**
     * Splits finds and splits by inner list commands
     * 
     * @param input input to be split
     * @return A string array of length two. First element is the string in the inner list command,
     *         while the second element is the string outside of the list command
     * @throws BackendException Exceptions related to invalid strings.
     */
    protected String[] splitByInnerListCommand (String input) throws BackendException {
        String treatedInput = input.trim() + COMMAND_SEPARATOR;
        if (!startsWithCommandStartIndicator(treatedInput)) { throw new BackendException(null,
                                                                                         INVALID_INNER_LIST_COMMAND_MESSAGE); }
        int endIndex = findIndexOfInnerListCommandEnd(treatedInput);
        if (endIndex == -1) { throw new BackendException(null, INVALID_INNER_LIST_COMMAND_MESSAGE); }
        String innerListCommand =
                treatedInput.substring(LIST_COMMAND_INDICATOR.length(),
                                       endIndex - LIST_COMMAND_END_INDICATOR.length()).trim();
        String outsideString = treatedInput.substring(endIndex).trim();
        String[] splitCommand = { innerListCommand, outsideString };
        return splitCommand;
    }

    private int findIndexOfInnerListCommandEnd (String input) {
        Stack<String> checkStack = new Stack<>();
        StringBuilder temporaryStringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            Character character = input.charAt(i);
            temporaryStringBuilder.append(character);
            if (COMMAND_SEPARATOR.equals(character.toString())) {
                String aggregatedWord = temporaryStringBuilder.toString().trim();
                if (aggregatedWord.equals(LIST_COMMAND_INDICATOR)) {
                    checkStack.push(aggregatedWord);
                }
                else if (aggregatedWord.equals(LIST_COMMAND_END_INDICATOR)) {
                    checkStack.pop();
                }
                if (checkStack.size() == 0) { return i; }
                temporaryStringBuilder.setLength(0);
            }
        }
        return -1;
    }

    protected boolean startsWithCommandStartIndicator (String input) {
        String splitInput = input.trim().split(COMMAND_SEPARATOR, 2)[0];
        return splitInput.equals(LIST_COMMAND_INDICATOR);
    }

    /**
     * Resets the command's state, in case it must be reused.
     */
    protected abstract void reset ();
}
