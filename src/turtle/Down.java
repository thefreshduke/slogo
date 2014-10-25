package turtle;

public class Down extends MoveDirection {

    @Override
    public Position move (Position p, int speed) {
        p.setYPos(p.getYPos() + speed);
        return p;
    }
}