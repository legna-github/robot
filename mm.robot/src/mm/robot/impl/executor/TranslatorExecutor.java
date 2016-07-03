package mm.robot.impl.executor;

import java.io.IOException;

import mm.robot.Executor;
import mm.robot.Robot;

public class TranslatorExecutor extends Dictionary implements Executor {

	private final Executor executor;
	
	public TranslatorExecutor(String dictionary, Executor executor) throws IOException {
		super(dictionary);
		this.executor = executor;
	}

	@Override
	public Robot execute(Robot robot, String command) {
		String[] commands = getProp().getProperty(command).split(",", -1);
		for (String cmd : commands) {
			if(cmd == null || (cmd = cmd.trim()).isEmpty()) {
				continue;
			}
			robot = executor.execute(robot, cmd);
		}
		return robot;
	}
}
