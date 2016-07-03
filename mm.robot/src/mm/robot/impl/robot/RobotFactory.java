package mm.robot.impl.robot;

import mm.robot.Robot;
import mm.robot.World;

public enum RobotFactory {
	erobot {
		@Override
		public Robot createRobot(World world, String spec) {
			return new RobotEnumDrivenImpl(world, spec);
		}
	},
	arobot {
		@Override
		public Robot createRobot(World world, String spec) {
			return new RobotArrayDrivenImpl(world, spec);
		}
	};
	
	public abstract Robot createRobot(World world, String spec);
}