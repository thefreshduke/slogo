package View;

public class TranslateFromGridtoModel {
    private Number startX;
    private Number startY;

    public TranslateFromGridtoModel (Number x, Number y) {
        startX = x;
        startY = y;
    }

    public double translateX (Number x) {
        return (double) x - (double) startX;
    }

    public double translateY (Number y) {
        return (double) y - (double) startY;
    }
}
