package mm.robot;

public class ArtifactImpl implements Artifact {
	
	private final int toughness;
	
	private final int power;
	
	
	public ArtifactImpl() {
		this(100, 100);
	}

	public ArtifactImpl(int power, int toughness) {
		super();
		this.toughness = toughness;
		this.power = power;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArtifactImpl [toughness=").append(toughness).append(", power=").append(power).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + power;
		result = prime * result + toughness;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtifactImpl other = (ArtifactImpl) obj;
		if (power != other.power)
			return false;
		if (toughness != other.toughness)
			return false;
		return true;
	}
	
}
