package mm.robot;

import static org.junit.Assert.assertEquals;

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

import mm.robot.impl.robot.RobotFactory;
import mm.robot.impl.world.WorldImpl;
import mm.robot.model.Position;

@RunWith(value = Parameterized.class)
public class RobotTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	public RobotTest(RobotFactory factory) {
		super();
		this.factory = factory;
	}

	@Test
	public void testRightUp() {
		World world = createWorld(new Position(1, 1));
		Robot robot = factory.createRobot(world, "E(3,-5)");
		assertEquals("E(3,-5)", robot.toString());
		assertEquals("E(4,-5)", robot.moveForward().toString());
		assertEquals("E(3,-6)", robot.moveRight().toString());
		assertEquals("E(2,-5)", robot.moveBackward().toString());
		assertEquals("E(3,-4)", robot.moveLeft().toString());
		assertEquals("S(3,-5)", robot.turnRight().toString());
		assertEquals("N(3,-5)", robot.turnLeft().toString());
	}

	@Test
	public void testRightDown() {
		World world = createWorld(new Position(1, -1));
		Robot robot = factory.createRobot(world, "E(3,-5)");
		assertEquals("E(3,-5)", robot.toString());
		assertEquals("E(4,-5)", robot.moveForward().toString());
		assertEquals("E(3,-4)", robot.moveRight().toString());
		assertEquals("E(2,-5)", robot.moveBackward().toString());
		assertEquals("E(3,-6)", robot.moveLeft().toString());
		assertEquals("S(3,-5)", robot.turnRight().toString());
		assertEquals("N(3,-5)", robot.turnLeft().toString());
	}

	@Test
	public void testLeftUp() {
		World world = createWorld(new Position(-1, 1));
		Robot robot = factory.createRobot(world, "E(3,-5)");
		assertEquals("E(3,-5)", robot.toString());
		assertEquals("E(2,-5)", robot.moveForward().toString());
		assertEquals("E(3,-6)", robot.moveRight().toString());
		assertEquals("E(4,-5)", robot.moveBackward().toString());
		assertEquals("E(3,-4)", robot.moveLeft().toString());
		assertEquals("S(3,-5)", robot.turnRight().toString());
		assertEquals("N(3,-5)", robot.turnLeft().toString());
	}

	@Test
	public void testLeftDown() {
		World world = createWorld(new Position(-1, -1));
		Robot robot = factory.createRobot(world, "E(3,-5)");
		assertEquals("E(3,-5)", robot.toString());
		assertEquals("E(2,-5)", robot.moveForward().toString());
		assertEquals("E(3,-4)", robot.moveRight().toString());
		assertEquals("E(4,-5)", robot.moveBackward().toString());
		assertEquals("E(3,-6)", robot.moveLeft().toString());
		assertEquals("S(3,-5)", robot.turnRight().toString());
		assertEquals("N(3,-5)", robot.turnLeft().toString());
	}

	private static World createWorld(Position uvector) {
		return WorldImpl.builder().setVector(uvector).setValidator(new Validator() {
			@Override
			public boolean isValid(Position vector) {
				return true;
			}
			
		}).build();
	}

}
