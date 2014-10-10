package commands;

import commandParser.CommandFactory;

public abstract class ArithmeticCommand extends LogicCommand {

    private BaseCommand myFirstExpression;
    private BaseCommand mySecondExpression;
    
    public ArithmeticCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
    }

    @Override
    protected void parseArguments (String userInput) {
        BaseCommand firstExpression = CommandFactory.createCommand(userInput, true);
        myFirstExpression = firstExpression;
        BaseCommand secondExpression = CommandFactory.createCommand(firstExpression.getLeftoverString(), true);
        mySecondExpression = secondExpression;
        setLeftoverCommands(mySecondExpression.getLeftoverString());
    }
    
    protected BaseCommand getFirstExpression(){
        return myFirstExpression;
    }
    
    protected BaseCommand getSecondExpression(){
        return mySecondExpression;
    }
}
