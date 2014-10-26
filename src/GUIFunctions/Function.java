package GUIFunctions;

public class Function extends UserInput {

	public Function(String name) {
		myName = name;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && other instanceof Function && ((Function) other)
				.getName().equals(myName));
	}
}
