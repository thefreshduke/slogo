package GUIFunctions;

public class DashedBorderStyle extends BorderStyle {
    public DashedBorderStyle () {
    }

    @Override
    public Double[] getStyle () {
        Double[] myDashes = { 5d, 5d };
        return myDashes;
    }

    public Double[] getStyle (int thickness) {
        Double[] myDashes = { (double) (5 * thickness), (double) (5 * thickness) };
        return myDashes;

    }
}
