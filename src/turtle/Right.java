package turtle;

public class Right extends MoveDirection{

	@Override
	public Position move(Position p, int speed) {
		p.setXPos(p.getXPos()+speed);
		return p;
	}
}

