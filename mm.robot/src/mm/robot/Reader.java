package mm.robot;

import java.util.Scanner;

public class Reader {

	private final Executor executor;
	
	public Reader(Executor executor) {
		super();
		this.executor = executor;
	}

	public Robot read(Robot robot, Scanner scanner) {
		while(scanner.hasNextLine()) {
			String command = scanner.nextLine();
			robot = executor.execute(robot, command);
		}
		return robot;
	}
}
