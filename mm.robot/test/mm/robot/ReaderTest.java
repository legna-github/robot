package mm.robot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mm.robot.impl.executor.CommandExecutor;
import mm.robot.impl.executor.TranslatorExecutor;
import mm.robot.impl.robot.RobotFactory;
import mm.robot.impl.world.WorldImpl;
import mm.robot.model.Position;

@RunWith(value = Parameterized.class)
public class ReaderTest {

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

	private RobotFactory factory;

	public ReaderTest(RobotFactory factory) {
		super();
		this.factory = factory;
	}

	@Test
	public void testRobotTypeA() throws IOException {
		testRobot("a");
	}

	@Test
	public void testRobotTypeB() throws IOException {
		testRobot("b");
	}

	@Test
	public void testRobotTypeC() throws IOException {
		testRobot("c");
	}

	@Test
	public void testRobotTypeD() throws IOException {
		testRobot("d");
	}
	
	@Test
	public void testRobotTypeE() throws IOException {
		testRobot("e");
	}

	@Test
	public void testall() throws IOException {
		String[] cases = {"a", "b", "c", "d", "e"};
		for (String testcase : cases) {
			testRobot(testcase);
		}
	}
	
	private void testRobot(String test) throws IOException {
		String[] specs = {"E(5,5)", "N(2,3)", "W(-3,5)", "S(5,-5)"};
		testRobot("driver_type_" + test + ".properties", "driver_type_" + test + "_test_data.txt", specs);
	}

	private void testRobot(String dictionary, String data_file, String... specs) throws IOException {
		for (String spec : specs) {
			testRobot(factory.createRobot(world, spec), dictionary, data_file);
		}
	}

	private void testRobot(Robot robot, String dictionary, String data_file) throws IOException {
		try(InputStream stream = getClass().getClassLoader().getResourceAsStream(data_file)) {
			try (Scanner scanner = new Scanner(stream)) {
				Reader reader = new Reader(new TranslatorExecutor(dictionary, new CommandExecutor()));
				Robot actual = reader.read(robot, scanner);
				assertEquals(robot, actual);
			}
		}
	}
	
	private static World createWorld() {
		return WorldImpl.builder().setVector(new Position(1, 1)).setValidator(new Validator() {
	
			@Override
			public boolean isValid(Position vector) {
				if(vector.getDx() < -10) {
					return false;
				}
				if(vector.getDx() > 10) {
					return false;
				}
				if(vector.getDy() < -10) {
					return false;
				}
				if(vector.getDy() > 10) {
					return false;
				}
				return true;
			}
			
		}).build();
	}

}
