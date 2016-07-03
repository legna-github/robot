package mm.robot.impl.strategy;

import mm.robot.Executor;
import mm.robot.Robot;
import mm.robot.Strategy;

public class SameRobotStrategy implements Strategy {

	private Executor executor;

	public SameRobotStrategy(Executor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public Robot execute(Robot robot, String command) {
		try {
			return executor.execute(robot, command);
		} catch (Exception ignore) {
		}
		return robot;
	}
	
	
}
