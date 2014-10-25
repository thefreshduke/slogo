package GUIFunctions;

public class DottedBorderStyle extends BorderStyle {
    public DottedBorderStyle () {
    }

    @Override
    public Double[] getStyle () {
        Double[] myDashes = { 1d, 5d };
        return myDashes;
    }

    public Double[] getStyle (int thickness) {
        double howThick = thickness;
        Double[] myDashes = { howThick * 1, 5 * howThick };
        return myDashes;
    }
}
