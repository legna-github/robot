package mm.robot.impl.executor;

import java.io.PrintWriter;

import mm.robot.Executor;
import mm.robot.Robot;

public class RecorderExecutor implements Executor {

	private final Executor executor;
	
	private final PrintWriter writer;

	public RecorderExecutor(PrintWriter writer, Executor executor) {
		super();
		this.executor = executor;
		this.writer = writer; 
	}

	@Override
	public Robot execute(Robot robot, String command) {
		Robot execute = executor.execute(robot, command);
		writer.println(command);
		return execute;
	}
	
	
}
