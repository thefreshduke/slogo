package GUIFunctions;

public class Variable {
	private String myName;
	private Double myValue;
	public Variable(String name, Double value){
		myName=name;
		myValue=value;
	}
	public String getName(){
		return myName;
	}
	public Double getValue(){
		return myValue;
	}
}
