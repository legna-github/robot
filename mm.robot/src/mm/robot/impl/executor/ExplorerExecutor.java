package mm.robot.impl.executor;

import java.io.IOException;
import java.util.Random;

import mm.robot.Executor;
import mm.robot.Explorer;
import mm.robot.Robot;

public class ExplorerExecutor extends Dictionary implements Explorer {

	private final Executor executor;
	
	public ExplorerExecutor(String dictionary, Executor executor) throws IOException {
		super(dictionary);
		this.executor = executor;
	}

	@Override
	public Robot explore(Robot robot) {
		
		Robot initial = robot;
		Random random = new Random();
		String[] commands = getProp().keySet().toArray(new String[]{});
		do {
			String command = commands[random.nextInt(commands.length)];
			robot = executor.execute(robot, command);
		}
		while(!initial.equals(robot));
		
		return robot;
	}
}
