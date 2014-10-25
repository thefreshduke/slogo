package turtle;

import javafx.scene.input.KeyCode;

public class Direction {
    private MoveDirection direction;

    public Direction (KeyCode e) {
        if (e == KeyCode.RIGHT) {
            direction = new Right();
        } else if (e == KeyCode.LEFT) {
            direction = new Left();
        } else if (e == KeyCode.UP) {
            direction = new Up();
        } else if (e == KeyCode.DOWN) {
            direction = new Down();
        } else
            direction = new NullDirection();
    }

    public Position move (Position p, int speed) {
        return direction.move(p, speed);
    }
}
