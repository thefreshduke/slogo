package commands.variableCommands;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.mathCommands.ArcTanCommand;
import commands.mathCommands.CosCommand;
import commands.mathCommands.DifferenceCommand;
import commands.mathCommands.ExponentCommand;
import commands.mathCommands.NaturalLogCommand;
import commands.mathCommands.NegativeCommand;
import commands.mathCommands.ProductCommand;
import commands.mathCommands.QuotientCommand;
import commands.mathCommands.RandomIntegerCommand;
import commands.mathCommands.RemainderCommand;
import commands.mathCommands.SinCommand;
import commands.mathCommands.SumCommand;
import commands.mathCommands.TanCommand;
import communicator.IVariableContainer;
import communicator.MapBasedVariableContainer;
import static org.junit.Assert.assertEquals;

public class VariableCommandParseTest {

//    @Test
//    public void testThatVariableIsAdded() {
//        setFactory();
//        IVariableContainer container = new MapBasedVariableContainer();
//        String variable1 = "variable1";
//        String value = " SUM 10 60";
//        String input = "MAKE " + variable1 + value;
//        BaseCommand variableSetCommand = CommandFactory.createCommand(input, false);
//        try {
//            variableSetCommand.execute(null, null, container);
//            BaseCommand command = container.getValue(variable1);
//            Double result = command.execute(null, null, container);
//            assertEquals(result, new Double(70));
//            
//            Double nonExistentResult = container.getValue("fff").execute(null, null, container);
//            assertEquals(nonExistentResult, new Double(0.0));
//        }
//        catch (Exception ex){
//            
//        }
//    }
    
    @Test
    public void testThatVariableIsGet() {
    	setFactory();
    	IVariableContainer container = new MapBasedVariableContainer();
        String variable1 = "variable1";
        String valueString = "SUM 10 60";
        BaseCommand value = CommandFactory.createCommand(valueString, true);
        String commandString = ":" + variable1;
        BaseCommand command = CommandFactory.createCommand(commandString, true);
        try{
        	container.addVariable(variable1, value);
        	Double result = command.execute(null,  null, container);
        	assertEquals(result, new Double(70));
        }
        catch (Exception ex){
        	
        }
    }
    
    private void setFactory(){
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
        
        commandToClassMap.put("MAKE", SetVariableCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}
