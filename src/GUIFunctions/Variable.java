package GUIFunctions;

public class Variable extends UserInput{
	private Double myValue;
	
	public Variable(String name, Double value){
		myName=name;
		myValue=value;
	}
	
	public Double getValue(){
		return myValue;
	}
}
