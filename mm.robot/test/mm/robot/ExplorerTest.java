package mm.robot;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mm.robot.impl.executor.CommandExecutor;
import mm.robot.impl.executor.ExplorerExecutor;
import mm.robot.impl.executor.RecorderExecutor;
import mm.robot.impl.executor.TranslatorExecutor;
import mm.robot.impl.robot.RobotFactory;
import mm.robot.impl.strategy.SameRobotStrategy;
import mm.robot.impl.world.WorldImpl;
import mm.robot.model.Position;

@RunWith(value = Parameterized.class)
public class ExplorerTest {

	private static World world;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world = createWorld();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

    @Parameters(name = "{index}: test({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {RobotFactory.erobot},
                {RobotFactory.arobot},
        });
    }
    
	private Robot robot;

	public ExplorerTest(RobotFactory factory) {
		super();
		robot = factory.createRobot(world, "E(5,5)");
	}

	@Test
	public void testDiscover() throws IOException {
		File file = new File(getClass().getResource("/").getFile(), "type_e_" + String.valueOf(System.currentTimeMillis()) + ".tmp");
		try(PrintWriter writer = new PrintWriter(file)){
			String dictionary = "driver_type_e.properties";
			new ExplorerExecutor(dictionary, new RecorderExecutor(writer, new TranslatorExecutor(dictionary, new SameRobotStrategy(new CommandExecutor())))).explore(robot);
		}
	}

	private static World createWorld() {
		return WorldImpl.builder().setVector(new Position(1, 1)).setValidator(new Validator() {
	
			@Override
			public boolean isValid(Position vector) {
				if(vector.getDx() > 50) {
					return false;
				}
				if(vector.getDx() < -50) {
					return false;
				}
				if(vector.getDy() > 50) {
					return false;
				}
				if(vector.getDy() < -50) {
					return false;
				}
				return true;
			}
			
		}).build();
	}

}
