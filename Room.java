import java.util.HashMap;

public class Room {

	protected String description;
	protected String shortDescription;
	protected java.util.HashMap<Action, Room> adjacentRooms;
	protected boolean roomWasVisited;

	public Room() {
		this("You are in a room", "Room", new java.util.HashMap<Action, Room>());
	}
	public Room(String description, String shortDescription, java.util.HashMap<Action, Room> adjacentRooms){
		
		this.roomWasVisited = false;
		this.description = description;
		this.shortDescription = shortDescription;
		this.adjacentRooms = adjacentRooms;
		// set reverse relationships in adjacent rooms with opposite directions
	}
	public final String toString() {
		String d = this.roomWasVisited ? this.shortDescription : this.description;
		this.roomWasVisited = true;
		return d;
	}
}
