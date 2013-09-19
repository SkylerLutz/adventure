import java.util.HashMap;

public class Room {

	protected String description;
	protected String shortDescription;
	protected HashMap<Action, Room> adjacentRooms;
	protected boolean roomWasVisited;

	public Room() {
		this("You are in a room", "Room");
	}
	public Room(String description, String shortDescription) {
		this(description, shortDescription, new HashMap<Action, Room>());
	}
	public Room(String description, String shortDescription, HashMap<Action, Room> adjacentRooms){
		
		this.roomWasVisited = false;
		this.description = description;
		this.shortDescription = shortDescription;
		this.adjacentRooms = adjacentRooms;

		// set reverse relationships in adjacent rooms with opposite directions
		for(Action a : Action.values()) {
			if(adjacentRooms.containsKey(a)) {
				Room adjacentRoom = this.adjacentRooms.get(a);
				adjacentRoom.setAdjacentRoom(Action.getOppositeDirection(a), this);
			}
		}
	}
	public void setAdjacentRoom(Action a, Room r) {
	
		this.adjacentRooms.put(a, r);
	}
	public Room getRoomForDirection(Action a) {
		if(canMoveToRoomInDirection(a)) {
			return this.adjacentRooms.get(a);
		}
		return null;
	}
	public boolean canMoveToRoomInDirection(Action a) {
		return this.adjacentRooms.containsKey(a);
	}


	public final String toString() {
		String d = this.roomWasVisited ? this.shortDescription : this.description;
		this.roomWasVisited = true;
		return d;
	}
	public String description(){ 

		return this.description;
	}
}
