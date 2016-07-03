package mm.robot;

import mm.robot.model.Position;

public interface World {

	Position mult(Position vector);

	Position ocupy(Position vector);

}