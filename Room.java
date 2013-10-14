import java.util.HashMap;
import java.util.LinkedList;

public class Room {

	protected String description;
	protected String shortDescription;
	protected HashMap<Action, Room> adjacentRooms;
	protected boolean roomWasVisited;
	protected LinkedList<Item> items;
	protected Player player;

	public Room() {
		this("You are in a room", "Room");
	}
	public Room(String description, String shortDescription) {
		this(description, shortDescription, new HashMap<Action, Room>(), new LinkedList<Item>());
	}
	public Room(String description, String shortDescription, HashMap<Action, Room> adjacentRooms, LinkedList<Item> items){
		
		this.roomWasVisited = false;
		this.description = description;
		this.shortDescription = shortDescription;
		
		if(items == null){
			this.items = new LinkedList<Item>();
		}
		else { 
			this.items = items;
		}
		
		if(adjacentRooms == null) {
			this.adjacentRooms = new HashMap<Action, Room>();
		}
		else {
			this.adjacentRooms = adjacentRooms;
		}

		// set reverse relationships in adjacent rooms with opposite directions
		for(Action a : Action.values()) {
			if(this.adjacentRooms.containsKey(a)) {
				Room adjacentRoom = this.adjacentRooms.get(a);
				adjacentRoom.setAdjacentRoom(a.getOppositeDirection(), this);
			}
		}
	}
	private void setAdjacentRoom(Action a, Room r) {
		this.adjacentRooms.put(a, r);
	}
	public void setAdjacentRooms(HashMap<Action, Room> rooms) {
		this.adjacentRooms.putAll(rooms);
		
		// set reverse relationships in adjacent rooms with opposite directions
		for(Action a : Action.values()) {
			if(this.adjacentRooms.containsKey(a)) {
				Room adjacentRoom = this.adjacentRooms.get(a);
				adjacentRoom.setAdjacentRoom(a.getOppositeDirection(), this);
			}
		}
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
	public void putItem(Item item) {
		this.items.add(item);
	}

	public Item takeItem(Item item) {
		if(item == null) {
			return null;
		}
		if(!item.canBePickedUp()) {
			System.out.println("You cannot pick up this item");
			return null;
		}
		if(this.items.remove(item)) {
			return item;
		}
		else {
			System.out.println("I don't see that here.");
			return null;
		}
	}
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	public String toString() {
		String s = "";
		// take this loop out before release, just print the detaildescription instead, because this reveals any and every item in the room, including hidden ones
		for(Item item : this.items) {
			s += "\nThere is a " + item.detailDescription() + " here.";
		}
		return this.description + s;
	}
	public String description(){ 
		String d = this.roomWasVisited ? this.shortDescription : this.description;
		this.roomWasVisited = true;
		return d;
	}
	public String shortDescription() {
		return this.shortDescription;
	}
}
