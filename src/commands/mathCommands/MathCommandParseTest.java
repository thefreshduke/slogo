package commands.mathCommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.controlCommands.IfElseCommand;
import commands.turtleCommands.ForwardCommand;

public class MathCommandParseTest {

    
    @Test
    public void testSumParses(){
        setFactory();
        String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(19.0));
        }
        catch(BackendException ex){
            
        }
    }
    
    @Test 
    public void testDifference(){
        setFactory();
        String input = "DIFFERENCE 6.8 SUM 7 8";
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(6.8 - (7 + 8)));
        }
        catch(BackendException ex){
            
        }
    }
    
    @Test
    public void testProduct(){
        setFactory();
        double first = 6.8;
        double second = 15;
        String input = "PRODUCT " + first + " " + second;
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(first * second));
        }
        catch(BackendException ex){
            
        }
    }
    
    public void testQuotient(){
        setFactory();
        double first = 6.8;
        double second = 5.3;
        String input = "QUOTIENT " + first + " " + second;
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(first/second));
        }
        catch(BackendException ex){
            
        }
    }
    
    public void testRemainder(){
        setFactory();
        double first = 6.8; 
        double second = 2.3;
        String input = "REMAINDER " + first + " " + second;
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(first % second));
        }
        catch(BackendException ex){
            
        }
    }
    
    @Test
    public void testRandom() {
        setFactory();
        int count = 50;
        while(count > 0){
            double max = Math.random()*count;
            String input = "RANDOM " + max;
            BaseCommand command = CommandFactory.createCommand(input, false);
            double result = 100;
            try {
                result = command.execute(null, null, null);
            }
            catch (BackendException e) {
            }
            assertTrue(result < max);
            count -= 1;
        }
    }
    
    @Test
    public void testMinus() {
        setFactory();
        double param = 264;
        String input = "MINUS " + param;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(-param));
    }
    
    @Test
    public void testPower() {
        setFactory();
        double first = 6.8; 
        double second = 2.3;
        String input = "POW " + first + " " + second;
        BaseCommand command = CommandFactory.createCommand(input, false);
        try{
            Double result = command.execute(null, null, null);
            assertEquals(result, new Double(Math.pow(first, second)));
        }
        catch(BackendException ex){
            
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
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.sin(Math.toRadians(angle))));
    }
    
    @Test
    public void testCos(){
        setFactory();
        double angle = 264;
        String input = "COS " + angle;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.cos(Math.toRadians(angle))));
    }
    
    @Test
    public void testTan(){
        setFactory();
        double angle = 264;
        String input = "TAN " + angle;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.tan(Math.toRadians(angle))));
    }
    
    @Test
    public void testATan(){
        setFactory();
        double angle = 264;
        String input = "ATAN " + angle;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.atan(Math.toRadians(angle))));
    }
    
    @Test
    public void NaturalLogTest() {
        setFactory();
        double param = 264;
        String input = "LOG " + param;
        BaseCommand command = CommandFactory.createCommand(input, false);
        Double result = null;
        try {
            result = command.execute(null, null, null);
        }
        catch (BackendException ex){
            
        }
        assertEquals(result, new Double(Math.log(param)));
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
        
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
}
