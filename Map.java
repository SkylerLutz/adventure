public class Map {

	public Map() {
	
		this("Unknown Map");
	}
	public Map(String name) {

		this.name = name;
	}

	protected String name;
	protected Room currentRoom;
}
