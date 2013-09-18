public abstract class Room {

	protected String description;
	protected String shortDescription;
	protected java.util.ArrayList<Room> adjacentRooms;
	protected boolean roomWasVisited;

	public Room() {
		this("You are in a room", "Room", new java.util.ArrayList<Room>());
	}
	public Room(String description, String shortDescription, java.util.ArrayList<Room> adjacentRooms){
		
		this.roomWasVisited = false;
		this.description = description;
		this.shortDescription = shortDescription;
		this.adjacentRooms = adjacentRooms;
	}
	public final String toString() {
		String d = this.roomWasVisited ? this.shortDescription : this.description;
		this.roomWasVisited = true;
		return d;
	}
}
