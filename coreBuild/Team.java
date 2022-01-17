package coreBuild;

public enum Team {
	white,
	black;
	
	public Team opposite() {
		return this == white? black : white;
	}
}
