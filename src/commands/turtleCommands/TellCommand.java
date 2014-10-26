package commands.turtleCommands;

import java.util.ArrayList;

import backendExceptions.BackendException;

public class TellCommand extends MultipleTurtleCommand {

    public TellCommand(String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute() throws BackendException {
        addFutureActiveTurtleTurtles();
        String strLastActiveID = myTurtleIDs[myTurtleIDs.length - 1];
        double lastActiveID = Double.parseDouble(strLastActiveID); 
        return lastActiveID;
    }

    @Override
    protected void parseArguments(String userInput) throws BackendException {
        String[] splitInput = splitByInnerListCommand(userInput);
        String innerInput = splitInput[0];
        myTurtleIDs = innerInput.split(COMMAND_SEPARATOR);

        if ((myTurtleIDs.length == 1) && (myTurtleIDs[0].equals(""))) {
            throw new BackendException(null, NO_SINGLE_ACTIVE_GRID);
        }
        myFutureActiveTurtleIDs = new ArrayList<>();
        String strTurtleID = "";
        int turtleID = 0;
        for (int i = 0; i < myTurtleIDs.length; i++) {
            strTurtleID = myTurtleIDs[i];
            if (isEven(i) && !strTurtleID.equals(CONSTANT_INDICATOR)) {
                throw new BackendException(null, INVALID_ERROR_MESSAGE);
            }
            else if (!isEven(i)) {
                turtleID = Integer.parseInt(strTurtleID);
                if (turtleID < 0) {
                    throw new BackendException(null, INVALID_TURTLE_ID_NEGATIVE_VALUE);
                }
                myFutureActiveTurtleIDs.add(turtleID);
            }
        }
        String nextCommandInput = splitInput[1];
        setLeftoverCommands(nextCommandInput);
    }
}