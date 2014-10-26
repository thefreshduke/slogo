package commands.turtleCommands;

import java.util.List;

import turtle.Turtle;
import backendExceptions.BackendException;

import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class IDCommand extends TurtleQuery {

    public IDCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        List<Turtle> turtleList = (List<Turtle>) turtle.getActiveTurtles();
        Turtle lastActiveTurtle = turtleList.get(turtle.getActiveTurtles().size() - 1);
        return lastActiveTurtle.getID();
    }
}
