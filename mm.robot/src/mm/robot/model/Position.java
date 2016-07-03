package mm.robot.model;

public class Position {

	private final int dx;

	private final int dy;
	
	public Position(int dx, int dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}
	
	public Position add(Position vector) {
		return new Position(this.dx + vector.dx, this.dy + vector.dy);
	}
	
	public Position mult(Position vector) {
		return new Position(this.dx * vector.dx, this.dy * vector.dy);
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	@Override
	public String toString() {
		return "Position [dx=" + dx + ", dy=" + dy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dx;
		result = prime * result + dy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (dx != other.dx)
			return false;
		if (dy != other.dy)
			return false;
		return true;
	}
	
}
