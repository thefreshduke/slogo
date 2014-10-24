package GUIFunctions;


public class DottedBorderStyle extends BorderStyle {
	public DottedBorderStyle(){
	}
	@Override
	public Double[] getStyle() {
		Double[] myDashes={1d, 5d};
		return myDashes;
	}
	public Double[] getStyle(int thickness){
		double howThick=thickness;
		Double[] myDashes={1*howThick, 5*howThick};
		return myDashes;
	}
}
