package turtle;

public class NullDirection extends MoveDirection {

    public NullDirection () {

    }

    @Override
    public Position move (Position p, int speed) {
        return null;
    }
}