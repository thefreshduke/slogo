package commands;

import java.util.Collection;
import java.util.Set;
import java.util.Stack;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.information.IInformationContainer;


/**
 * 
 * Abstract class for a Turtle command. The update method will be implemented and
 * returns the turtle object that can be manipulated by other commands. All other
 * types of commands will extend the BaseCommand, such as commands that only
 * modify the view, commands that are conditional, mathematical operations.
 *
 */
public abstract class BaseCommand {
    private BaseCommand myNextCommand;
    private BaseCommand myInternalCommand;
    private String myLeftoverString = "";
    private boolean myExpressionFlag;
    protected final String COMMAND_DELIMITER = "\\s+";
    protected static String COMMAND_INDICATOR = "liststart";
    protected static String COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
    protected static String VARIABLE_INDICATOR = "variable";

    /**
     * 
     * @param userInput
     * @throws BackendException TODO
     */
    public BaseCommand (String userInput, boolean isExpression) throws BackendException {
        myExpressionFlag = isExpression;
        parseArguments(userInput);
    }

    /**
     * Method returns the computation of the turtle command
     * 
     * @throws BackendException TODO
     * 
     */

    public double execute() throws BackendException {
        double result = onExecute();
        if(getNextCommand() != null){
            return getNextCommand().execute();
        }
        reset();
        return result;
    }

    protected abstract double onExecute () throws BackendException;

    public abstract Set<Class<? extends IInformationContainer>> getRequiredInformationTypes ();

    public abstract void setRequiredInformation (Collection<IInformationContainer> containers);

    private BaseCommand getNextCommand () {
        return myNextCommand;
    }

    protected abstract void parseArguments (String userInput) throws BackendException;

    public String getLeftoverString () {
        return myLeftoverString;
    }

    protected void setLeftoverCommands (String string) throws BackendException{
        if (myExpressionFlag) {
            myLeftoverString = string;
        }
        else if (string != null && string != "") {
            myNextCommand = CommandFactory.createCommand(string, false);
        }
    }

    protected String[] splitByInnerListCommand (String input) {
        String treatedInput = input.trim() + COMMAND_SEPARATOR;
        if (!startsWithCommandStartIndicator(treatedInput)) {
            // exception
        }
        int endIndex = findIndexOfInnerListCommandEnd(treatedInput);
        if (endIndex == -1) {
            // exception
        }
        String innerListCommand =
                treatedInput.substring(COMMAND_INDICATOR.length(),
                        endIndex - COMMAND_END_INDICATOR.length()).trim();
        String outsideString = treatedInput.substring(endIndex).trim();
        String[] splitCommand = { innerListCommand, outsideString};
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
                if (aggregatedWord.equals(COMMAND_INDICATOR)) {
                    checkStack.push(aggregatedWord);
                }
                else if (aggregatedWord.equals(COMMAND_END_INDICATOR)) {
                    checkStack.pop();
                }
                if (checkStack.size() == 0) { 
                    return i; 
                }
                temporaryStringBuilder.setLength(0);
            }
        }
        return -1;
    }

    protected boolean startsWithCommandStartIndicator (String input) {
        String splitInput = input.trim().split(COMMAND_SEPARATOR, 2)[0];
        return splitInput.equals(COMMAND_INDICATOR);
    }
    
    protected abstract void reset();
}