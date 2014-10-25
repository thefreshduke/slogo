package commands.viewCommands;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.expressionCommands.ArcTanCommand;
import commands.expressionCommands.CosCommand;
import commands.expressionCommands.DifferenceCommand;
import commands.expressionCommands.ExponentCommand;
import commands.expressionCommands.NaturalLogCommand;
import commands.expressionCommands.NegativeCommand;
import commands.expressionCommands.ProductCommand;
import commands.expressionCommands.QuotientCommand;
import commands.expressionCommands.RandomIntegerCommand;
import commands.expressionCommands.RemainderCommand;
import commands.expressionCommands.SinCommand;
import commands.expressionCommands.SumCommand;
import commands.expressionCommands.TanCommand;
import commands.information.BaseVariableContainer;
import commands.information.MapBasedVariableContainer;

public class ViewCommandParseTest {

    // @Test
    // public void testThatVariableIsAdded() {
    // setFactory();
    // IVariableContainer container = new MapBasedVariableContainer();
    // String variable1 = "variable1";
    // String value = " SUM 10 60";
    // String input = "MAKE " + variable1 + value;
    // BaseCommand variableSetCommand = CommandFactory.createCommand(input,
    // false);
    // try {
    // variableSetCommand.execute(null, null, container);
    // BaseCommand command = container.getValue(variable1);
    // Double result = command.execute(null, null, container);
    // assertEquals(result, new Double(70));
    //
    // Double nonExistentResult = container.getValue("fff").execute(null, null,
    // container);
    // assertEquals(nonExistentResult, new Double(0.0));
    // }
    // catch (Exception ex){
    //
    // }
    // }

    @Test
    public void testThatVariableIsGet () {
        setFactory();
        BaseVariableContainer container = new MapBasedVariableContainer();
        String variable1 = "variable1";
        String valueString = "SUM 10 60";
        BaseCommand value = CommandFactory.createCommand(valueString, true);
        String commandString = ":" + variable1;
        BaseCommand command = CommandFactory.createCommand(commandString, true);
        try {
            container.addVariable(variable1, value);
            Double result = command.execute();
            assertEquals(result, new Double(70));
        } catch (Exception ex) {

        }
    }

    private void setFactory () {
        Map<String, Class> commandToClassMap = new HashMap<>();
        commandToClassMap.put("SUM", SumCommand.class);
        commandToClassMap.put("DIFFERENCE", DifferenceCommand.class);
        commandToClassMap.put("PRODUCT", ProductCommand.class);
        commandToClassMap.put("QUOTIENT", QuotientCommand.class);
        commandToClassMap.put("REMAINDER", RemainderCommand.class);
        commandToClassMap.put("RANDOM", RandomIntegerCommand.class);
        commandToClassMap.put("POW", ExponentCommand.class);

        commandToClassMap.put("MINUS", NegativeCommand.class);
        commandToClassMap.put("SIN", SinCommand.class);
        commandToClassMap.put("COS", CosCommand.class);
        commandToClassMap.put("TAN", TanCommand.class);
        commandToClassMap.put("ATAN", ArcTanCommand.class);
        commandToClassMap.put("LOG", NaturalLogCommand.class);

        // commandToClassMap.put("MAKE", SetVariableCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}