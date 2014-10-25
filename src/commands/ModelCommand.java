package commands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ModelCommand extends BaseCommand {

    public ModelCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

}
