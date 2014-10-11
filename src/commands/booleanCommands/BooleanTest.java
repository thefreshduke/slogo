package commands.booleanCommands;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.mathCommands.RandomIntegerCommand;
import commands.mathCommands.SumCommand;

public class BooleanTest {

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
    
//    @Test
//    public void testIfElse(){
//        setFactory();
//        String input = "IFELSE SUM 6 7 [ SUM 7 7 ] [ SUM 2 3 ]";
//        BaseCommand command = CommandFactory.createCommand(input, false);
//        try{
//            double result = command.execute(null, null);
//            assertEquals(new Double(result), new Double(14));
//        }
//        catch(BackendException ex){
//            
//        }
//       
//    }
    
    private void setFactory(){
        Map commandToClassMap = new HashMap<>();
        commandToClassMap.put("LESSP", LessCommand.class);
        commandToClassMap.put("EQUAL", EqualCommand.class);
        commandToClassMap.put("AND", AndCommand.class);
        commandToClassMap.put("NOT", NotCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}
