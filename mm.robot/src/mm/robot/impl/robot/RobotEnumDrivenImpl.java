package mm.robot.impl.robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mm.robot.Robot;
import mm.robot.World;
import mm.robot.model.Position;

class RobotEnumDrivenImpl implements Robot {

	private static final Pattern PATTERN = Pattern.compile("([NESW])\\(([+-]?\\d+),([+-]?\\d+)\\)");
	
	private final Orientation orientation;
	
	private final Position position;

	private final World world;
	
	public RobotEnumDrivenImpl(World world, String spec) {
		this(world, parseSepcs(world, spec));
	}

	private RobotEnumDrivenImpl(World world, ConstructorArgumentsHolder holder) {
		this(world, holder.orientation, holder.position);
	}

	private RobotEnumDrivenImpl(World world, Orientation orientation, Position vector) {
		super();
		this.orientation = orientation;
		this.position = vector;
		this.world = world;
		// no validation on the private constructor to avoid overriding the validation strategy on validate()
	}

	@Override
	public Robot moveForward() {
		return orientation.moveForward(this);
	}

	@Override
	public Robot moveRight() {
		return orientation.moveRight(this);
	}

	@Override
	public Robot moveBackward() {
		return orientation.moveBackward(this);
	}

	@Override
	public Robot moveLeft() {
		return orientation.moveLeft(this);
	}

	@Override
	public Robot turnRight() {
		return orientation.turnRight(this);
	}

	@Override
	public Robot turnLeft() {
		return orientation.turnLeft(this);
	}

	@Override
	public String toString() {
		return toString(orientation.toString(), position);
	}

	private static String toString(String orientation, Position position) {
		return String.format("%s(%d,%d)", orientation, position.getDx(), position.getDy());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
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
		RobotEnumDrivenImpl other = (RobotEnumDrivenImpl) obj;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	private static ConstructorArgumentsHolder parseSepcs(World world, String spec) {
		Matcher matcher = PATTERN.matcher(spec);
		if(matcher.find()) {
			ConstructorArgumentsHolder holder = new ConstructorArgumentsHolder();
			holder.orientation = OrientationStateMachine.valueOf(matcher.group(1));
			
			holder.position = world.ocupy(new Position(Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3))));
			return holder;
		}
		throw new IllegalArgumentException("Failure to match vector spec : " + spec);
	}

	private static class ConstructorArgumentsHolder {
		
		private Orientation orientation;
		
		private Position position;

	}
	
	private interface Orientation {
		RobotEnumDrivenImpl moveForward(RobotEnumDrivenImpl robot);
		RobotEnumDrivenImpl moveRight(RobotEnumDrivenImpl robot);
		RobotEnumDrivenImpl moveBackward(RobotEnumDrivenImpl robot);
		RobotEnumDrivenImpl moveLeft(RobotEnumDrivenImpl robot);
		RobotEnumDrivenImpl turnRight(RobotEnumDrivenImpl robot);
		RobotEnumDrivenImpl turnLeft(RobotEnumDrivenImpl robot);

	}
	
	private static enum OrientationStateMachine implements Orientation {

		N(new Position(0, 1)) {

			@Override
			public RobotEnumDrivenImpl moveRight(RobotEnumDrivenImpl robot) {
				return E.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveBackward(RobotEnumDrivenImpl robot) {
				return S.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveLeft(RobotEnumDrivenImpl robot) {
				return W.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl turnRight(RobotEnumDrivenImpl robot) {
				return E.turn(robot);
			}

			@Override
			public RobotEnumDrivenImpl turnLeft(RobotEnumDrivenImpl robot) {
				return W.turn(robot);
			}

		}, 
		E(new Position(1, 0)) {

			@Override
			public RobotEnumDrivenImpl moveRight(RobotEnumDrivenImpl robot) {
				return S.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveBackward(RobotEnumDrivenImpl robot) {
				return W.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveLeft(RobotEnumDrivenImpl robot) {
				return N.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl turnRight(RobotEnumDrivenImpl robot) {
				return S.turn(robot);
			}

			@Override
			public RobotEnumDrivenImpl turnLeft(RobotEnumDrivenImpl robot) {
				return N.turn(robot);
			}
		}, 
		S(new Position(0, -1)) {

			@Override
			public RobotEnumDrivenImpl moveRight(RobotEnumDrivenImpl robot) {
				return W.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveBackward(RobotEnumDrivenImpl robot) {
				return N.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveLeft(RobotEnumDrivenImpl robot) {
				return E.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl turnRight(RobotEnumDrivenImpl robot) {
				return W.turn(robot);
			}

			@Override
			public RobotEnumDrivenImpl turnLeft(RobotEnumDrivenImpl robot) {
				return E.turn(robot);
			}
		}, 
		W(new Position(-1, 0)) {

			@Override
			public RobotEnumDrivenImpl moveRight(RobotEnumDrivenImpl robot) {
				return N.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveBackward(RobotEnumDrivenImpl robot) {
				return E.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl moveLeft(RobotEnumDrivenImpl robot) {
				return S.move(this, robot);
			}

			@Override
			public RobotEnumDrivenImpl turnRight(RobotEnumDrivenImpl robot) {
				return N.turn(robot);
			}

			@Override
			public RobotEnumDrivenImpl turnLeft(RobotEnumDrivenImpl robot) {
				return S.turn(robot);
			}
		};
		
		private static final Position NULL_POSITION = new Position(0, 0);

		@Override
		public RobotEnumDrivenImpl moveForward(RobotEnumDrivenImpl robot) {
			return move(this, robot);
		}

		private final Position vector;
		
		private OrientationStateMachine(Position vector) {
			this.vector = vector;
		}

		protected RobotEnumDrivenImpl turn(RobotEnumDrivenImpl robot) {
			return createRobot(NULL_POSITION, robot.world, this, robot.position);
		}

		private RobotEnumDrivenImpl move(Orientation orientation, RobotEnumDrivenImpl robot) {
			return createRobot(this.vector, robot.world, orientation, robot.position);
		}

		private RobotEnumDrivenImpl createRobot(Position vector, World world, Orientation orientation, Position position) {
			Position next = world.mult(vector).add(position);
			String spec = RobotEnumDrivenImpl.toString(orientation.toString(), next);
			return new RobotEnumDrivenImpl(world, spec);
		}
	}
}
