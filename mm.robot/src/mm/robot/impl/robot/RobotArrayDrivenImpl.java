package mm.robot.impl.robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mm.robot.Robot;
import mm.robot.World;
import mm.robot.model.Position;
class RobotArrayDrivenImpl implements Robot {
	
	private static final String[] orientations = {"N", "E", "S", "W"};
	
	private static final Position[] baseVectors = {  new Position(0,1), new Position(1,0), new Position(0,-1), new Position(-1,0)};

	private static final Position NULL_POSITION = new Position(0, 0);

	private static final Pattern PATTERN = Pattern.compile("([NESW])\\(([+-]?\\d+),([+-]?\\d+)\\)");
	
	private final int orientation;
	
	private final Position position;

	private final World world;
	
	public RobotArrayDrivenImpl(World world, String spec) {
		this(world, parseSepcs(world, spec));
	}

	private RobotArrayDrivenImpl(World world, ConstructorArgumentsHolder holder) {
		this(world, holder.orientation, holder.position);
	}

	private RobotArrayDrivenImpl(World world, int orientation, Position position) {
		super();
		this.orientation = orientation;
		this.position = position;
		this.world = world;
	}

	@Override
	public Robot moveForward() {
		return move(index(0));
	}

	@Override
	public Robot moveRight() {
		return move(index(1));
	}

	@Override
	public Robot moveBackward() {
		return move(index(2));
	}

	@Override
	public Robot moveLeft() {
		return move(index(3));
	}

	@Override
	public Robot turnRight() {
		return turn(index(1));
	}

	@Override
	public Robot turnLeft() {
		return turn(index(3));
	}

	@Override
	public String toString() {
		return toString(orientations[orientation], position);
	}

	private String toString(String orientation, Position position) {
		return String.format("%s(%d,%d)", orientation, position.getDx(), position.getDy());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orientation;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		RobotArrayDrivenImpl other = (RobotArrayDrivenImpl) obj;
		if (orientation != other.orientation)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	private int index(int i) {
		return (orientation + i)%orientations.length;
	}

	private RobotArrayDrivenImpl turn(int index) {
		return createRobot(NULL_POSITION, world, index, position);
	}

	private RobotArrayDrivenImpl move(int index) {
		return createRobot(baseVectors[index], world, orientation, position);
	}

	private RobotArrayDrivenImpl createRobot(Position vector, World world, int orientation, Position position) {
		Position next = world.mult(vector).add(position);
		return new RobotArrayDrivenImpl(world, toString(orientations[orientation], next));
	}

	private static ConstructorArgumentsHolder parseSepcs(World world, String spec) {
		Matcher matcher = PATTERN.matcher(spec);
		if(matcher.find()) {
			ConstructorArgumentsHolder holder = new ConstructorArgumentsHolder();
			for (int i = 0; i < orientations.length; i++) {
				if(orientations[i].equals(matcher.group(1))) {
					holder.orientation = i;
					break;
				}
			}
			holder.position = world.ocupy(new Position(Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3))));
			return holder;
		}
		throw new IllegalArgumentException("Failure to match vector spec : " + spec);
	}

	private static class ConstructorArgumentsHolder {
		
		private int orientation;
		
		private Position position;

	}
}
