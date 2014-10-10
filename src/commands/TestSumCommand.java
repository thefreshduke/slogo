package commands;


public class TestSumCommand extends LogicCommand{
    
    private BaseCommand myFirstExpression;
    private BaseCommand mySecondExpression;
    
    public TestSumCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
    }

    @Override
    public double execute () {
        double returnValue = executeCommand(myFirstExpression) + executeCommand(mySecondExpression);
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) {
        BaseCommand firstExpression = TestFactory.createCommand(userInput, true);
        myFirstExpression = firstExpression;
        BaseCommand secondExpression = TestFactory.createCommand(firstExpression.getLeftoverString(), true);
        mySecondExpression = secondExpression;
        setLeftoverCommands(mySecondExpression.getLeftoverString());
    }
}
