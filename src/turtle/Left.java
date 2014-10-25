package turtle;

public class Left extends MoveDirection {

	@Override
	public Position move(Position p, int speed) {
		p.setXPos(p.getXPos()-speed);
		return p;
		
	}

}
