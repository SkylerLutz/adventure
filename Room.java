import java.util.HashMap;
import java.util.LinkedList;

public class Room implements Comparable {


	public Room() {
		this("You are in a room", "Room");
	}
	public Room(String description, String shortDescription){
		
		this.roomWasVisited = false;
		this.description = description;
		this.shortDescription = shortDescription;
		this.items = new LinkedList<Item>();
		this.adjacentRooms = new HashMap<Action, Room>();

		/*
		// set reverse relationships in adjacent rooms with opposite directions
		for(Action a : Action.values()) {
			if(this.adjacentRooms.containsKey(a)) {
				Room adjacentRoom = this.adjacentRooms.get(a);
				adjacentRoom.setAdjacentRoom(a.getOppositeDirection(), this);
			}
		}
		*/

		this.transitionMessages = new HashMap<Action, String>();
	}
	protected void setAdjacentRoom(Action a, Room r) {
		this.adjacentRooms.put(a, r);
	}
	public void setOneWayAdjacentRoom(Action a, Room r) {
		setAdjacentRoom(a, r);
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
	public Action getDirectionForRoom(Room room) {
		for(Action a : this.adjacentRooms.keySet()) {
			if(this.adjacentRooms.get(a).compareTo(room) == 0) {
				return a;
			}
		}
		return Action.ActionUnknown;
	}
	public boolean canMoveToRoomInDirection(Action a) {
		return this.adjacentRooms.containsKey(a);
	}
	public void setAdjacentRoomTransitionMessage(String message, Action direction) {
		this.transitionMessages.put(direction, message);
	}
	public HashMap<Action, String> transitionMessages() {
		return this.transitionMessages;
	} 
	public void putItem(Item item) {
		this.items.add(item);
	}
	public void putItems(LinkedList<Item> items) {
		for(Item i : items) {
			this.items.add(i);
		}
	}

	public Item takeItem(Item item) {
		if(item == null) {
			return null;
		}
		else if(this.items.contains(item)) {
			
			if(item.canBePickedUp()) {
				this.items.remove(item);
				return item;
			}
			else {
				System.out.println("You cannot pick up this item");
			}
		}
		else {
			System.out.println("I don't see that here.");
		}
		return Item.ItemUnknown;
	}
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	public String toString() {
		String s = "";
		// take this loop out before release, just print the detaildescription instead, because this reveals any and every item in the room, including hidden ones
		for(Item item : this.items) {
			if(item.isVisible()) {
				s += "\nThere is a " + item.detailDescription() + " here.";
			}
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
	public int compareTo(Object other) {
		if(toString().compareTo(other.toString()) == 0) {
			return 0;
		}
		return -1;
	}
	protected String description;
	protected String shortDescription;
	protected HashMap<Action, Room> adjacentRooms;
	protected HashMap<Action, String> transitionMessages;
	protected boolean roomWasVisited;
	protected LinkedList<Item> items;
	protected Player player;
}
