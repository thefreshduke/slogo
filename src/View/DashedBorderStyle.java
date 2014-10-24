package View;

public class DashedBorderStyle extends BorderStyle {
	public DashedBorderStyle(){
	}
	@Override
	public Double[] getStyle() {
		Double[] myDashes={5d, 5d};
		return myDashes;
	}
}
