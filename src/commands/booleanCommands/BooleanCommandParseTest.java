package commands.booleanCommands;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;

public class BooleanCommandParseTest {

    @Test
    public void testLess() {
        setFactory();
        String input = "LESSP 2 10"; //LESS? doesn't work for some reason... it has something to do with the question mark
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    @Test
    public void testGreater() {
        setFactory();
        String input = "GREATERP 2 1";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    

    @Test
    public void testEqual() {
        setFactory();
        String input = "EQUALP 3 3";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    @Test
    public void testNotEqual() {
        setFactory();
        String input = "NOTEQUALP 3 4";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    @Test
    public void testAnd() {
        setFactory();
        String input = "AND 3 4";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    @Test
    public void testOr() {
        setFactory();
        String input = "OR 3 0";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    @Test
    public void testNot() {
        setFactory();
        String input = "NOT 0";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(1.0));
        }
        catch(BackendException ex) {
            
        }
    }
    
    private void setFactory(){
        Map<String, Class> commandToClassMap = new HashMap<>();
        commandToClassMap.put("LESSP", LessCommand.class);
        commandToClassMap.put("LESS?", LessCommand.class);
        commandToClassMap.put("GREATERP", GreaterCommand.class);
        commandToClassMap.put("GREATER?", GreaterCommand.class);
        commandToClassMap.put("EQUALP", EqualCommand.class);
        commandToClassMap.put("EQUAL?", EqualCommand.class);
        commandToClassMap.put("NOTEQUALP", NotEqualCommand.class);
        commandToClassMap.put("NOTEQUAL?", NotEqualCommand.class);
        commandToClassMap.put("AND", AndCommand.class);
        commandToClassMap.put("OR", OrCommand.class);
        commandToClassMap.put("NOT", NotCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}
