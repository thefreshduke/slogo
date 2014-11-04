package commands;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;

import commands.information.BaseUserDefinedContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class LogicCommand extends BaseCommand {
    private SlogoView myView;
    private Turtle myTurtle;
    private BaseUserDefinedContainer myVariableContainer;

    public LogicCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    protected abstract double onExecute () throws BackendException;

    protected double executeCommand (BaseCommand command) throws BackendException {
        return command.onExecute();
    }
}