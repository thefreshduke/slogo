package commands.mathCommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ForwardCommand;
import commands.IfElseCommand;

public class MathCommandParseTest {

    
    @Test
    public void testSumParses(){
        setFactory();
        String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null);
            assertEquals(result, new Double(19.0));
        }
        catch(BackendException ex){
            
        }
    }
    
    @Test public void testDifference(){
        setFactory();
        String input = "DIFFERENCE 6.8 SUM 7 8";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null);
            assertEquals(result, new Double(6.8 - (7 + 8)));
        }
        catch(BackendException ex){
            
        }
    }
    
    @Test
    public void testRandom(){
        setFactory();
        int count = 50;
        while(count > 0){
            double max = Math.random()*count;
            String input = "RANDOM " + max;
            BaseCommand command = CommandFactory.createCommand(input, false);
            double result = 100;
            try {
                result = command.execute(null, null);
            }
            catch (BackendException e) {
            }
            assertTrue(result < max);
            count -= 1;
        }
    }
    
    @Test
    public void testSin(){
        setFactory();
        double angle = 264;
        String input = "SIN " + angle;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.sin(angle)));
    }
    
    private void setFactory(){
        Map commandToClassMap = new HashMap<>();
        commandToClassMap.put("SUM", SumCommand.class);
        commandToClassMap.put("IFELSE", IfElseCommand.class);
        commandToClassMap.put("FD", ForwardCommand.class);
        commandToClassMap.put("RANDOM", RandomIntegerCommand.class);
        commandToClassMap.put("DIFFERENCE", DifferenceCommand.class);
        commandToClassMap.put("SIN", SinCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}
