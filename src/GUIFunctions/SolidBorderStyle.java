package GUIFunctions;


public class SolidBorderStyle extends BorderStyle {
	public SolidBorderStyle(){
	}
	@Override
	public Double[] getStyle() {
		Double[] myDashes={1d, 0d};
		return myDashes;
	}
}
