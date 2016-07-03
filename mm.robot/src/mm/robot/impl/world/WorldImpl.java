package mm.robot.impl.world;

import mm.robot.Validator;
import mm.robot.World;
import mm.robot.model.Position;

public class WorldImpl implements World {

	private final Validator validator;

	private  final Position vector;
	
	private WorldImpl(Position vector, Validator validator) {
		super();
		this.vector = vector;
		this.validator = validator;
	}

	public static Builder builder() {
		return new Builder();
	}


	@Override
	public Position mult(Position vector) {
		return this.vector.mult(vector);
	}

	@Override
	public Position ocupy(Position position) {
		if(!validator.isValid(position)) {
			throw new IllegalArgumentException(String.format("Position[%s] is off-limits", position));
		};
		return position;
	}


	public static class Builder {
			
		private  Position vector;
		
		private Validator validator;

		private Builder() {
			super();
		}

		public Position getVector() {
			return vector;
		}

		public Builder setVector(Position vector) {
			this.vector = vector;
			return this;
		}

		public Validator getValidator() {
			return validator;
		}

		public Builder setValidator(Validator validator) {
			this.validator = validator;
			return this;
		}
		
		public World build() {
			return new WorldImpl(vector, validator);
		}
	}
}
