package mm.robot.impl.executor;

import mm.robot.Executor;
import mm.robot.Robot;

public class CommandExecutor implements Executor {

	@Override
	public Robot execute(Robot robot, String command) {
		return CommandFactory.getCommand(command).execute(robot);
	}

	private interface Command<R> {
		R execute(R r);
	}

	private static enum CommandFactory implements Command<Robot> {
		moveForward() {
			@Override
			public Robot execute(Robot r) {
				return r.moveForward();
			}
		},
		moveRight() {
			@Override
			public Robot execute(Robot r) {
				return r.moveRight();
			}
		},
		moveBackward() {
			@Override
			public Robot execute(Robot r) {
				return r.moveBackward();
			}
		},
		moveLeft() {
			@Override
			public Robot execute(Robot r) {
				return r.moveLeft();
			}
		},
		turnRight() {
			@Override
			public Robot execute(Robot r) {
				return r.turnRight();
			}
		},
		turnLeft() {
			@Override
			public Robot execute(Robot r) {
				return r.turnLeft();
			}
		};
	
		@Override
		public abstract Robot execute(Robot r);
	
		public static Command<Robot> getCommand(String cmd) {
			return CommandFactory.valueOf(cmd);
		}
	}

}
