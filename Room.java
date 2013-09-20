import java.util.HashMap;
import java.util.LinkedList;

public class Room {

	protected String description;
	protected String shortDescription;
	protected HashMap<Action, Room> adjacentRooms;
	protected boolean roomWasVisited;
	protected LinkedList<Item> items;

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
		this.adjacentRooms = adjacentRooms;
		this.items = items;
		
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

	public void putItem(Item item) {
		this.items.add(item);
	}

	public Item takeItem(Item item) {
		if(this.items.remove(item)) {
			return item;
		}
		else {
			return null;
		}
	}
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	public void installItemIntoItem(Item installingItem, Item installEnclosure) {
		this.items.get(this.items.indexOf(installEnclosure)).setInstalledItem(installingItem);
	}
	public final String toString() {
		String d = this.roomWasVisited ? this.shortDescription : this.description;
		this.roomWasVisited = true;
		return d;
	}
	public String description(){ 
		String s = "";
		for(Item item : this.items) {
			s += "\nThere is a " + item.detailDescription() + " here.";
		}
		return this.description + s;
	}

}
