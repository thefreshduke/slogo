package GUIFunctions;

public class Variable extends UserInput {
	private Double myValue;

	public Variable(String name, Double value) {
		myName = name;
		myValue = value;
	}

	public Double getValue() {
		return myValue;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && other instanceof Variable && compareNameAndValue((Variable) other));
	}

	private boolean compareNameAndValue(Variable other) {
		return other.getName().equals(myName)
				&& other.getValue().equals(myValue);
	}
}
